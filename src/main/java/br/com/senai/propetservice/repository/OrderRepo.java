package br.com.senai.propetservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senai.propetservice.models.PetShopServiceOrder;

@Repository
public interface OrderRepo extends JpaRepository<PetShopServiceOrder, Long>{

    
}
