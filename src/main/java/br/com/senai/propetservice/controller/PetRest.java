package br.com.senai.propetservice.controller;

import br.com.senai.propetservice.data.PetDto;
import br.com.senai.propetservice.data.UserDto;
import br.com.senai.propetservice.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;

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

    @GetMapping(value = "/{pet_id}")
    @Operation(summary = "Get pet by id", tags = {"Pet"})
    public PetDto getPet(@PathVariable("pet_id") Long petId) {
        return service.getPet(petId);
    }

    @GetMapping(value = "/{pet_id}/owner")
    @Operation(summary = "Find pet owner", tags = {"Pet"})
    public UserDto getPetByUser(@PathVariable("pet_id") Long petId) {
        return service.getPetOwner(petId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all pets", tags = {"Pet"})
    public Page<PetDto> getAllPets() {
        return null;
    }

    @DeleteMapping(value = "/{pet_id}")
    @Operation(summary = "Remove a pet", tags = {"Pet"})
    public void deletePet(@PathVariable("pet_id") Long id) {

    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update pet information", tags = {"Pet"})
    public void updatePet(@RequestBody PetDto pet) {

    }
}
