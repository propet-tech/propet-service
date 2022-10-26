package br.com.senai.propetservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import br.com.senai.propetservice.data.PetShopServicingDto;
import br.com.senai.propetservice.service.PetShopService;

@RestController
@Tag(name = "Service", description = "PetShop services endpoint")
@RequestMapping("/service")
public class PetShopServiceRest {

    @Autowired
    private PetShopService petShopService;

    @Operation(summary = "Create new petshop service")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createService(@RequestBody PetShopServicingDto service) {

    }

    @Operation(summary = "Delete a petshop service")
    @DeleteMapping(value = "/{service_id}")
    public void deleteService(@PathVariable("service_id") Long id) {

    }

    @Operation(summary = "Find petshop service by id")
    @GetMapping(value = "/{service_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PetShopServicingDto getPetShopService(@PathVariable("service_id") Long ind) {
        return null;
    }

    @Operation(summary = "Update info for petshop service")
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updatePetShopService(@RequestBody PetShopServicingDto service) {

    }

    @Operation(summary = "Get all available petshop services")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<PetShopServicingDto> getAllPetShopServices(
        @RequestParam("page") Integer page,
        @RequestParam("size") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return petShopService.getAllServices(pageable);
    }
}
