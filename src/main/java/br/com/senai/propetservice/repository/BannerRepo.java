package br.com.senai.propetservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senai.propetservice.models.Banner;

@Repository
public interface BannerRepo extends JpaRepository<Banner, Long> {
}
