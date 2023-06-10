package br.com.senai.propetservice.repository;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;

import br.com.senai.propetservice.config.FileStorageConfig;
import br.com.senai.propetservice.models.exceptions.FileStorageException;

@Repository
public class FileSystemRepository {

    private final Path config;

    @Autowired
    public FileSystemRepository(FileStorageConfig config) {
        this.config = java.nio.file.Paths.get(config.getUploadDir()).toAbsolutePath().normalize();
    }

    public UUID save(InputStream fileStream) {
        try {
            var fileName = UUID.randomUUID(); 
            Path targetLocation = this.config.resolve(fileName.toString());
            Files.copy(fileStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (Exception e) {
            throw new FileStorageException("Error saving file", e);
        }
    }

    public void update(InputStream fileStream, UUID fileName) {
        try {
            Path targetLocation = this.config.resolve(fileName.toString());
            Files.copy(fileStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new FileStorageException("Error updating file", e);
        }
    }

    public FileSystemResource load(UUID filename) {
        try {
            Path filePath = config.resolve(filename.toString());
            return new FileSystemResource(filePath);
        } catch (Exception e) {
            throw new FileStorageException("Error loading file", e);
        }
    }
}
