package br.com.senai.propetservice.controller;

import javax.annotation.security.RolesAllowed;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.propetservice.data.PetShopDto;
import br.com.senai.propetservice.models.enums.ServiceStatus;
import br.com.senai.propetservice.service.PetShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Service", description = "PetShop services endpoint")
@RequestMapping("/service")
public class PetShopRest {

    @Autowired
    private PetShopService petShopService;

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update Status of a petshop service")
    public void test(@PathVariable Long id, @RequestParam ServiceStatus status) {
        petShopService.updateServiceStatus(id, status);
    }

    @Operation(summary = "Create new petshop service")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createService(@RequestBody PetShopDto service) {
        petShopService.createService(service);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Delete a petshop service")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteService(@PathVariable("id") Long id) {
        petShopService.deleteService(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Find petshop service by id")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PetShopDto getPetShopService(@PathVariable("id") Long id) {
        return petShopService.getPetShopService(id);
    }

    @Operation(summary = "Update info for petshop service")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePetShopService(@RequestBody PetShopDto service) {
        petShopService.updatePetShop(service);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Get all available petshop services")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<PetShopDto> getAllPetShopServices(
        @ParameterObject @PageableDefault Pageable pageable
    ) {
        return petShopService.getAllServices(pageable);
    }

    @GetMapping(
        value = "/active",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Get all active petshop services")
    public Page<PetShopDto> getAllActive(
        @ParameterObject @PageableDefault Pageable pageable
    ) {
        return petShopService.getAllActive(pageable);
    }

    @PreAuthorize("hasRole('ROLE_PETSHOP_ADMIN')")
    @Operation(summary = "Get number of petshop services availables")
    @GetMapping(value = "/count")
    public Long getNumberOfPetShop() {
        return petShopService.getNumberOfPetShop();
    }
}
