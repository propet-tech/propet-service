package br.com.senai.propetservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.senai.propetservice.data.response.BannerResponseDto;
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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @RolesAllowed("admin")
    @Operation(summary = "Add banner image")
    public ResponseEntity<?> addBanner(@RequestPart(required = true) MultipartFile image) {
        bannerService.addBanner(image);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @Operation(summary = "List all banners")
    public List<BannerResponseDto> listBanners() {
        return bannerService.listBanners();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "remove banner")
    public void removeBanner(@RequestParam("id") Long id) {
        
    }
}
