package br.com.senai.propetservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senai.propetservice.models.FileReference;

public interface FileReferenceRepo extends JpaRepository<FileReference, Long> {
}
