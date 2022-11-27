package br.com.senai.propetservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senai.propetservice.models.PetBreed;

@Repository
public interface BreedRepo extends JpaRepository<PetBreed, Long> {}
