package br.com.senai.propetservice.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.propetservice.models.Pet;

@Repository
public interface PetRepo extends JpaRepository<Pet, Long>, JpaSpecificationExecutor<Pet> {

    @Query("SELECT p FROM Pet p WHERE p.user.id = :userId")
    Page<Pet> getAllByClient(Pageable Pageable, UUID userId);

    // @Query("SELECT count(p) FROM Pet p WHERE p.client.id =:clientId")
    // Long countByClient(@Param("clientId") Long clientId);
}
