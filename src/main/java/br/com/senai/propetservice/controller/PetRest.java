package br.com.senai.propetservice.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.propetservice.data.request.PetRequestDto;
import br.com.senai.propetservice.data.response.PetResponseDto;
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

    @PostMapping
    @Operation(summary = "Create a new Pet")
    public ResponseEntity<?> createPet(@RequestBody PetRequestDto pet, JwtAuthenticationToken principal) {
        service.createPet(pet, principal);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @Operation(summary = "Get all pets")
    public Page<PetResponseDto> getAllPets(
            @ParameterObject @PageableDefault Pageable pageable,
            JwtAuthenticationToken principal) {
        return service.getAllPets(pageable, principal);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Get pet by id")
    public PetResponseDto getPet(@PathVariable("id") Long petId) {
        return service.getPet(petId);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Remove a pet")
    public ResponseEntity<?> deletePet(@PathVariable("id") Long id) {
        service.deletePet(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping()
    @Operation(summary = "Update pet information")
    public ResponseEntity<?> updatePet(@RequestBody PetRequestDto pet) {
        service.updatePet(pet);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/count")
    @RolesAllowed({ "PETSHOP_ADMIN" })
    @Operation(summary = "count number of pets for all clients")
    public Long countPet() {
        return service.getNumberOfPets();
    }
}
