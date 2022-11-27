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

import br.com.senai.propetservice.data.ClientDto;
import br.com.senai.propetservice.data.response.PetResponseDto;
import br.com.senai.propetservice.service.ClientService;
import br.com.senai.propetservice.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/client")
@Tag(name = "Client", description = "Endpoint to manager clients")
public class ClientRest {

    @Autowired
    private ClientService service;

    @Autowired
    private PetService petService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new client", tags = {"Client"})
    public ResponseEntity<?> createClient(@RequestBody ClientDto client) {
        service.create(client);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Get a client by id", tags = {"Client"})
    public ClientDto getClientById(@PathVariable("id") Long id) {
        return service.getClientById(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update client information", tags = {"Client"})
    public ResponseEntity<?> updateClient(@RequestBody ClientDto client) {
        service.updateClient(client);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/{id}/pet")
    @Operation(summary = "Get all pets of specific client", tags = {"Client"})
    public Page<PetResponseDto> getAllPetsByOwner(
        @PathVariable("id") Long clientId,
        @ParameterObject @PageableDefault Pageable pageable
    ) {
        return petService.getAllPetsByOwner(clientId, pageable);    
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Remove a client", tags = {"Client"})
    public ResponseEntity<?> deleteClient(@PathVariable("id") Long id) {
        service.deleteClient(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/{id}/pet/count")
    @Operation(summary = "count number of pets of specific client")
    public Long countPetOfClient(@PathVariable("id") Long clientId) {
        return petService.getNumberOfPetsByClient(clientId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all clients")
    public Page<ClientDto> getAllClients(
        @ParameterObject @PageableDefault Pageable pageable
    ) {
        return service.getAllClients(pageable);
    }
}
