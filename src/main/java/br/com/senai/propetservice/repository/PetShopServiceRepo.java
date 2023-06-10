package br.com.senai.propetservice.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.propetservice.models.PetShopService;
import br.com.senai.propetservice.models.enums.OrderStatus;

@Repository
public interface PetShopServiceRepo extends JpaRepository<PetShopService, Long> {

    // @Query("SELECT ps FROM PetShopService as ps WHERE ps.active = true")
    // Page<PetShopService> findAllActive(Pageable pageable);
    //
    // @Modifying
    // @Query("UPDATE PetShopService SET status = :status WHERE id = :id")
    // void changeServiceStatus(Long id, OrderStatus status);
    //
    // @Query("SELECT ps FROM PetShopService as ps WHERE ps.user.id = :id")
    // Page<PetShopService> findAllByUser(Pageable pageable, UUID id);
}
