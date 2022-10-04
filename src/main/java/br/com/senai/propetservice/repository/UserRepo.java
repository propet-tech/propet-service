package br.com.senai.propetservice.repository;

import br.com.senai.propetservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
