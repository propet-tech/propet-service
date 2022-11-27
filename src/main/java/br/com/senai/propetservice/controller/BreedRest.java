package br.com.senai.propetservice.controller;

import javax.websocket.server.PathParam;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.propetservice.data.BreedDto;
import br.com.senai.propetservice.service.BreedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/breed")
@Tag(name = "Pet Breed", description = "EndPoint to manager pet breeds")
public class BreedRest {
    
    @Autowired
    private BreedService breedService;

    @GetMapping
    @Operation(summary = "Get all pet breeds")
    public Page<BreedDto> getAll(
        @ParameterObject @PageableDefault Pageable pageable
    ) {
        return breedService.getAll(pageable);
    }

    @PostMapping
    @Operation(summary = "Add new breed")
    public ResponseEntity<?> create(@RequestBody BreedDto dto) {
        breedService.create(dto); 
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "remove pet breed")
    public ResponseEntity<?> delete(@PathParam("id") Long id) {
        breedService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
