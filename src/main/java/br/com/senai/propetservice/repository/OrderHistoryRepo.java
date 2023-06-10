package br.com.senai.propetservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.propetservice.models.PetShopOrderHistory;

@Repository
public interface OrderHistoryRepo extends JpaRepository<PetShopOrderHistory, Long>, JpaSpecificationExecutor<PetShopOrderHistory> {

    @Query("SELECT h FROM PetShopOrderHistory h WHERE h.petshopOrder.id = :id")
    List<PetShopOrderHistory> findAllByOder(Long id);
}
