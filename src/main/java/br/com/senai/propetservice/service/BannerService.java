package br.com.senai.propetservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;

import org.springframework.util.MimeType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.senai.propetservice.converters.BannerMapper;
import br.com.senai.propetservice.data.response.BannerResponseDto;
import br.com.senai.propetservice.models.Banner;
import br.com.senai.propetservice.models.exceptions.NotFoundException;
import br.com.senai.propetservice.repository.BannerRepo;
import jakarta.transaction.Transactional;

@Service
public class BannerService {

    @Autowired
    private FileLocationService storage;

    @Autowired
    private BannerRepo bannerRepo;

    @Autowired
    private BannerMapper mapper;

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

    public void addBanner(MultipartFile image) {
        try {
            var file = storage.save(image.getInputStream(), image.getContentType());
            var banner = new Banner();
            banner.setImage(file);
            bannerRepo.save(banner);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public List<BannerResponseDto> listBanners() {
        return bannerRepo.findAll().stream().map(
            (banner) -> mapper.map(banner)
        ).toList();
    }

    public void removeBanner(Long id) {
        bannerRepo.deleteById(id);
        // TODO deletar aquivo
    }
}
