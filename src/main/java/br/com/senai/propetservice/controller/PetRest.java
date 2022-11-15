package br.com.senai.propetservice.controller;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.propetservice.data.PetDto;
import br.com.senai.propetservice.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pet")
@Tag(name = "Pet", description = "Pet manager endpoint")
public class PetRest {

    @Autowired
    private PetService service;

    @PostMapping()
    @Operation(summary = "Create a new Pet", tags = {"Pet"})
    public ResponseEntity<?> createPet(@RequestBody PetDto pet) {
        service.createPet(pet);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Get pet by id", tags = {"Pet"})
    public PetDto getPet(@PathVariable("id") Long petId) {
        return service.getPet(petId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all pets", tags = {"Pet"})
    public Page<PetDto> getAllPets(
        @ParameterObject @PageableDefault Pageable pageable
    ) {
        return service.getAllPets(pageable);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Remove a pet", tags = {"Pet"})
    public ResponseEntity<?> deletePet(@PathVariable("id") Long id) {
        service.deletePet(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update pet information", tags = {"Pet"})
    public ResponseEntity<?> updatePet(@RequestBody PetDto pet) {
        service.updatePet(pet);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/count")
    @Operation(summary = "count number of pets for all users", tags = {"Pet"})
    public Long countPet() {
        return service.getNumberOfPets();
    }
}
