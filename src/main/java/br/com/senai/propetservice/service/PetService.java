package br.com.senai.propetservice.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import br.com.senai.propetservice.converters.PetMapper;
import br.com.senai.propetservice.data.request.PetRequestDto;
import br.com.senai.propetservice.data.response.PetResponseDto;
import br.com.senai.propetservice.models.Pet;
import br.com.senai.propetservice.models.exceptions.NotFoundException;
import br.com.senai.propetservice.repository.PetRepo;
import br.com.senai.propetservice.repository.filters.PetFilters;
import br.com.senai.propetservice.util.FilterBuilder;
import br.com.senai.propetservice.util.PrincipalUtils;

@Service
public class PetService {

    @Autowired
    private PetRepo repository;

    @Autowired
    private PetMapper mapper;

    public Page<PetResponseDto> getAllPets(Pageable pageable, JwtAuthenticationToken principal) {
        FilterBuilder<Pet> builder = new FilterBuilder<>();
        var filters = new PetFilters();

        if (!PrincipalUtils.userHasAuthority(principal.getAuthorities(), "ROLE_PETSHOP_ADMIN")) {
            builder.add(filters.byUserId(UUID.fromString(principal.getName())));
        }

        var filter = builder.build();
        return repository.findAll(filter, pageable).map(pet -> mapper.map(pet));
    }

    public void createPet(PetRequestDto pet, JwtAuthenticationToken principal) {
        if (!PrincipalUtils.userHasAuthority(principal.getAuthorities(), "ROLE_PETSHOP_ADMIN")) {
            pet.setUserId(UUID.fromString(principal.getName()));
        }

        repository.save(mapper.map(pet));
    }

    public PetResponseDto getPet(Long id) {
        Pet pet = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Pet Not Found"));
        return mapper.map(pet);
    }


    public void deletePet(Long id) {
        repository.deleteById(id);
    }

    public void updatePet(PetRequestDto pet) {
        repository.save(
                mapper.map(pet));
    }

    public Long getNumberOfPets() {
        return repository.count();
    }

    // public Long getNumberOfPetsByClient(Long clientId) {
    // if (clientRepo.existsById(clientId))
    // return repository.countByClient(clientId);
    // else
    // throw new NotFoundException("Client not found");
    // }
}
