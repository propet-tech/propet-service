package br.com.senai.propetservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senai.propetservice.models.PetShop;

public interface PetShopRepo extends JpaRepository<PetShop, Long> {

}
