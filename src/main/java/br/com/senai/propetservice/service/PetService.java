package br.com.senai.propetservice.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import br.com.senai.propetservice.controller.PetRest;
import br.com.senai.propetservice.converters.PetMapper;
import br.com.senai.propetservice.data.request.PetRequestDto;
import br.com.senai.propetservice.data.response.PetResponse;
import br.com.senai.propetservice.models.FileReference;
import br.com.senai.propetservice.models.Pet;
import br.com.senai.propetservice.models.exceptions.NotFoundException;
import br.com.senai.propetservice.repository.PetRepo;
import br.com.senai.propetservice.repository.filters.PetFilters;
import br.com.senai.propetservice.util.FilterBuilder;
import br.com.senai.propetservice.util.PrincipalUtils;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PetService {

    @Autowired
    private PetRepo repository;

    @Autowired
    private FileLocationService storage;

    @Autowired
    private PetMapper mapper;

    @Transactional
    public void createPet(PetRequestDto pet, MultipartFile image, JwtAuthenticationToken principal) {
        var newPet = mapper.map(pet);
        newPet.setUser(UUID.fromString(principal.getName()));
        var id = repository.save(newPet).getId();
        savePetImage(id, image, principal);
    }

    public ResponseEntity<FileSystemResource> getPetImage(Long id) {
        if (!repository.existsById(id))
            throw new NotFoundException("Pet Not Found");

        var image = repository.findPetImage(id).orElseThrow(
                () -> new NotFoundException("This pet has no image"));

        var resource = storage.loadFileAsResource(image.getId());
        var mediaType = MediaType.asMediaType(
                MimeType.valueOf(image.getContentType()));

        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(resource);
    }

    public void savePetImage(Long id, MultipartFile image, JwtAuthenticationToken principal) {
        // Simplificar essa bagaça
        log.debug("Storing pet %s image", id);

        if (!image.getContentType().matches("^image\\/(png|gif|jpeg)$"))
            throw new RuntimeException("File is not a image");

        if (!repository.existsByIdAndUserId(id, UUID.fromString(principal.getName())))
            throw new NotFoundException("Pet Not Found");

        try {
            var dbfile = repository.findPetImage(id);
            FileReference dbImage;

            if (dbfile.isEmpty()) {
                dbImage = storage.save(image.getInputStream(), image.getContentType());
            } else {
                var file = dbfile.get();
                dbImage = storage.update(image.getInputStream(), image.getContentType(), file.getId());
            }

            repository.setPetImage(dbImage, id);
        } catch (Exception e) {
            throw new RuntimeException("",e);
            // throw new FileStorageException("Error saving file");
        }
    }

    @Transactional
    public void removePetImage(Long id, JwtAuthenticationToken principal) {
        // TODO implementar remover image
        // var image = repository.findPetImage(id).orElseThrow(
        // () -> new NotFoundException("Image not Found"));
        // fileRepo.deleteById(image.getId());
    }

    public Page<PetResponse> getAllPets(Pageable pageable, JwtAuthenticationToken principal) {
        log.debug("Listing all pets");
        FilterBuilder<Pet> builder = new FilterBuilder<>();
        var filters = new PetFilters();

        if (!PrincipalUtils.userHasAuthority(principal.getAuthorities(), "ROLE_PETSHOP_ADMIN")) {
            builder.add(filters.byUserId(UUID.fromString(principal.getName())));
        }

        var filter = builder.build();
        return repository.findAll(filter, pageable).map(pet -> mapper.map(pet));
    }

    private String createImageUrl(Long id) {
        return MvcUriComponentsBuilder
                .fromMethodCall(MvcUriComponentsBuilder.on(PetRest.class).getPetImage(id)).toUriString();
    }


    public PetResponse getPet(Long id) {
        Pet pet = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Pet Not Found"));

        var result = mapper.map(pet);

        if (pet.getImage() != null)
            result.setImage(createImageUrl(id));

        return result;
    }

    public void deletePet(Long id) {
        // TODO implementar usando o campo inactive
        repository.deleteById(id);
    }

    @Transactional
    public void updatePet(PetRequestDto pet, MultipartFile image, JwtAuthenticationToken principal) {
        var peta = repository.findById(pet.getId()).orElseThrow(
            () -> new NotFoundException("Pet Not Found")
        );
        var entity = mapper.map(pet);
        entity.setUser(peta.getUser());
        entity.setImage(peta.getImage());
        repository.save(entity);
        if (image != null)
            savePetImage(peta.getId(), image, principal);
    }

    public Long getNumberOfPets() {
        return repository.count();
    }
}
