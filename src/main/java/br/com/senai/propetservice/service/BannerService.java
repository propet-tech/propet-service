package br.com.senai.propetservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;

import org.springframework.util.MimeType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.senai.propetservice.models.exceptions.NotFoundException;
import br.com.senai.propetservice.repository.BannerRepo;

@Service
public class BannerService {

    @Autowired
    private FileLocationService storage;

    @Autowired
    private BannerRepo bannerRepo;

    public ResponseEntity<FileSystemResource> getBannerImage(Long id) {
        final var banner = bannerRepo.findById(id).orElseThrow(
                () -> new NotFoundException("Banner Not Found"));

        final var image = banner.getImage();

        var resource = storage.loadFileAsResource(image.getId());
        var mediaType = MediaType.asMediaType(
                MimeType.valueOf(image.getContentType()));

        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(resource);
    }
}
