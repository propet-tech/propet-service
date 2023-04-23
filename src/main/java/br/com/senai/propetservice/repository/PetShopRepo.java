package br.com.senai.propetservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.propetservice.models.PetShop;
import br.com.senai.propetservice.models.enums.ServiceStatus;

@Repository
public interface PetShopRepo extends JpaRepository<PetShop, Long> {

    @Query("SELECT ps FROM PetShop as ps WHERE ps.active = true")
    Page<PetShop> findAllActive(Pageable pageable);

    @Modifying
    @Query("UPDATE PetShop SET status = :status WHERE id = :id")
    void changeServiceStatus(Long id, ServiceStatus status);
}
