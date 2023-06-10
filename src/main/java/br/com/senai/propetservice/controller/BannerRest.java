package br.com.senai.propetservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.senai.propetservice.service.BannerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/banner")
@Tag(name = "Banner", description = "Banner manager endpoint")
public class BannerRest {

    @Autowired
    private BannerService bannerService;

    @GetMapping("/{id}")
    @Operation(summary = "Get banner image")
    public ResponseEntity<FileSystemResource> getBannerImage(@PathVariable("id") Long id) {
        return bannerService.getBannerImage(id);
    }

    @PostMapping
    @RolesAllowed("admin")
    @Operation(summary = "Add banner image")
    public ResponseEntity<?> addBanner(@RequestPart(required = true) MultipartFile image) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @Operation(summary = "List all banners")
    public String listBanners() {
        return null;
    }
}
