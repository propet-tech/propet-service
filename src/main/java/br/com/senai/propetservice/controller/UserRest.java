package br.com.senai.propetservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.senai.propetservice.data.UserDto;
import br.com.senai.propetservice.service.UserService;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User", description = "Endpoint to manager users")
public class UserRest {

    @Autowired
    private UserService service;

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

}
