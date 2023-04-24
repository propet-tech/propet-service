package br.com.senai.propetservice.repository;

import br.com.senai.propetservice.models.Pet;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepo extends JpaRepository<Pet, Long> {

    @Query("SELECT p FROM Pet p WHERE p.user.id = :userId")
    Page<Pet> getAllByClient(Pageable Pageable, UUID userId);

    // @Query("SELECT count(p) FROM Pet p WHERE p.client.id =:clientId")
    // Long countByClient(@Param("clientId") Long clientId);
}
