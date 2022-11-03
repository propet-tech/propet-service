package br.com.senai.propetservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.senai.propetservice.data.PetShopDto;
import br.com.senai.propetservice.service.PetShopService;

@RestController
@Tag(name = "Service", description = "PetShop services endpoint")
@RequestMapping("/service")
public class PetShopRest {

    @Autowired
    private PetShopService petShopService;

    @Operation(summary = "Create new petshop service")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createService(@RequestBody PetShopDto service) {
        petShopService.createService(service);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Delete a petshop service")
    @DeleteMapping(value = "/{service_id}")
    public void deleteService(@PathVariable("service_id") Long id) {
        petShopService.deleteService(id);
    }

    @Operation(summary = "Find petshop service by id")
    @GetMapping(value = "/{service_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PetShopDto getPetShopService(@PathVariable("service_id") Long id) {
        return petShopService.getPetShopService(id);
    }

    @Operation(summary = "Update info for petshop service")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updatePetShopService(@RequestBody PetShopDto service) {
        petShopService.updatePetShop(service);
    }

    @Operation(summary = "Get all available petshop services")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<PetShopDto> getAllPetShopServices(
        @RequestParam("page") Integer page,
        @RequestParam("size") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return petShopService.getAllServices(pageable);
    }

    @Operation(summary = "Get number of petshop services availables")
    @GetMapping(value = "/count")
    public Long getNumberOfPetShop() {
        return petShopService.getNumberOfPetShop();
    }
}
