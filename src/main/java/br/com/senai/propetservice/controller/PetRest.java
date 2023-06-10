package br.com.senai.propetservice.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.senai.propetservice.data.request.PetRequestDto;
import br.com.senai.propetservice.data.response.PetResponse;
import br.com.senai.propetservice.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/pet")
@Tag(name = "Pet", description = "Pet manager endpoint")
public class PetRest {

    @Autowired
    private PetService service;

    @Operation(summary = "Get pet image")
    @GetMapping(value = "/{id}/image")
    public ResponseEntity<FileSystemResource> getPetImage(@PathVariable("id") Long id) {
        return service.getPetImage(id);
    }

    @Operation(summary = "Create a new Pet")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createPet(@RequestPart PetRequestDto pet,
            @RequestPart(required = false) MultipartFile image, JwtAuthenticationToken principal) {
        service.createPet(pet, image, principal);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Update pet information")
    public ResponseEntity<?> updatePet(@RequestPart PetRequestDto pet,
            @RequestPart(required = false) MultipartFile image, JwtAuthenticationToken principal) {
        service.updatePet(pet, image, principal);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @Operation(summary = "Add pet image")
    @PostMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadPetImage(@PathVariable("id") Long id, @RequestPart("file") MultipartFile image,
            JwtAuthenticationToken principal) {
        service.savePetImage(id, image, principal);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Remove pet image")
    @DeleteMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadPetImage(@PathVariable("id") Long id, JwtAuthenticationToken principal) {
        service.removePetImage(id, principal);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    @Operation(summary = "Get all pets")
    public Page<PetResponse> getAllPets(
            @ParameterObject @PageableDefault Pageable pageable,
            JwtAuthenticationToken principal) {
        return service.getAllPets(pageable, principal);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Get pet by id")
    public PetResponse getPet(@PathVariable("id") Long petId) {
        return service.getPet(petId);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Remove a pet")
    public ResponseEntity<?> deletePet(@PathVariable("id") Long id) {
        service.deletePet(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/count")
    @RolesAllowed({ "admin" })
    @Operation(summary = "count number of pets for all clients")
    public Long countPet() {
        return service.getNumberOfPets();
    }
}
