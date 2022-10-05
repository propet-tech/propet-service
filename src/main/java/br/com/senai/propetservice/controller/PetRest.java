package br.com.senai.propetservice.controller;

import br.com.senai.propetservice.data.PetDto;
import br.com.senai.propetservice.service.PetService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/{user_id}/pet")
@Tag(name = "Pet", description = "Pet manager endpoint")
public class PetRest {

    @Autowired
    private PetService service;

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createPet(@RequestBody PetDto pet) {
        return null;
    }

    @GetMapping(value = "/{pet_id}")
    public PetDto getPet(@PathVariable("user_id") Long userId, @PathVariable("pet_id") Long petId) {
        return null;
    }
}
