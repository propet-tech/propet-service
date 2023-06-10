package br.com.senai.propetservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.propetservice.models.Pet;
import br.com.senai.propetservice.models.FileReference;

@Repository
public interface PetRepo extends JpaRepository<Pet, Long>, JpaSpecificationExecutor<Pet> {

    @Query("""
        SELECT CASE WHEN COUNT(1) > 0 THEN true ELSE false END FROM Pet p 
        WHERE p.id = :petId and p.user = :userId
    """)
    boolean existsByIdAndUserId(Long petId, UUID userId);

    @Query("SELECT p.image FROM Pet p WHERE p.id = :id")
    Optional<FileReference> findPetImage(Long id);

    @Modifying
    @Query("UPDATE Pet SET image = :image WHERE id = :id")
    void setPetImage(FileReference image, Long id);
}
