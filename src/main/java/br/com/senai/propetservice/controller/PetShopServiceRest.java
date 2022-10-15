package br.com.senai.propetservice.controller;

import br.com.senai.propetservice.data.PetShopServiceDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Service", description = "PetShop services endpoint")
@RequestMapping("/service")
public class PetShopServiceRest {

    @Operation(summary = "Create new petshop service")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createService(@RequestBody PetShopServiceDto service) {

    }

    @Operation(summary = "Delete a petshop service")
    @DeleteMapping(value = "/{service_id}")
    public void deleteService(@PathVariable("service_id") Long id) {

    }

    @Operation(summary = "Find petshop service by id")
    @GetMapping(value = "/{service_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PetShopServiceDto getPetShopService(@PathVariable("service_id") Long ind) {
        return null;
    }

    @Operation(summary = "Update info for petshop service")
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updatePetShopService(@RequestBody PetShopServiceDto service) {

    }

    @Operation(summary = "Get all available petshop services")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<PetShopServiceDto> getAllPetShopServices() {
        return null;
    }
}
