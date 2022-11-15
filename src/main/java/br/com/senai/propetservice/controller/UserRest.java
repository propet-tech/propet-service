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
import br.com.senai.propetservice.data.UserDto;
import br.com.senai.propetservice.service.PetService;
import br.com.senai.propetservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "Endpoint to manager users")
public class UserRest {

    @Autowired
    private UserService service;

    @Autowired
    private PetService petService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new user", tags = {"User"})
    public ResponseEntity<?> createUser(@RequestBody UserDto user) {
        service.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Get a user by id", tags = {"User"})
    public UserDto getUserById(@PathVariable("id") Long id) {
        return service.getUserById(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update user information", tags = {"User"})
    public ResponseEntity<?> updateUser(@RequestBody UserDto user) {
        service.updateUser(user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/{id}/pet")
    @Operation(summary = "Get all pets of specific user", tags = {"User"})
    public Page<PetDto> getAllPetsByOwner(
        @PathVariable("id") Long userId,
        @ParameterObject @PageableDefault Pageable pageable
    ) {
        return petService.getAllPetsByOwner(userId, pageable);    
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Remove a user", tags = {"User"})
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        service.removeUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/{id}/pet/count")
    @Operation(summary = "count number of pets of specific user")
    public Long countPetOfUser(@PathVariable("id") Long userId) {
        return petService.getNumberOfPetsByUser(userId);
    }
}
