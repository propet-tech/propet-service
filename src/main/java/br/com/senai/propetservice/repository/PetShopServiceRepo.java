package br.com.senai.propetservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senai.propetservice.models.PetShopService;
import br.com.senai.propetservice.repository.search.SearchRepository;

@Repository
public interface PetShopServiceRepo extends JpaRepository<PetShopService, Long>, SearchRepository<PetShopService> {
}
