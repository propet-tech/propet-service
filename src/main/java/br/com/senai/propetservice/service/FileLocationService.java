package br.com.senai.propetservice.service;

import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import br.com.senai.propetservice.models.FileReference;
import br.com.senai.propetservice.models.exceptions.NotFoundException;
import br.com.senai.propetservice.repository.FileReferenceRepo;
import br.com.senai.propetservice.repository.FileSystemRepository;
import jakarta.transaction.Transactional;

@Service
public class FileLocationService {

    @Autowired
    private FileSystemRepository fileSystemRepository;

    @Autowired
    private FileReferenceRepo fileReferenceRepo;

    @Transactional
    public FileReference save(InputStream fileStream, String contentType) {
        UUID fileName = fileSystemRepository.save(fileStream);
        return fileReferenceRepo.save(new FileReference(fileName, contentType));
    }

    @Transactional
    public FileReference update(InputStream fileStream, String contentType, Long id) {
        FileReference dbFile = fileReferenceRepo.findById(id).orElseThrow(
            () -> new NotFoundException("File not found")
        );
        dbFile.setContentType(contentType);
        fileSystemRepository.update(fileStream, dbFile.getFileName());
        return fileReferenceRepo.save(dbFile);
    }

    public FileReference findFileMetadata(Long fileId) {
        return fileReferenceRepo.findById(fileId).orElseThrow(
            () -> new NotFoundException(String.format("File %s not found", fileId))
        );
    }

    public FileSystemResource loadFileAsResource(Long fileId) {
        var dbFile = fileReferenceRepo.findById(fileId).orElseThrow(
            () -> new NotFoundException(String.format("File %s not found", fileId))
        );
        return fileSystemRepository.load(dbFile.getFileName());
    }
}
