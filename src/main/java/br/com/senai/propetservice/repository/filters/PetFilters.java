package br.com.senai.propetservice.repository.filters;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import br.com.senai.propetservice.models.Pet;
import br.com.senai.propetservice.models.Pet_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class PetFilters {

    public Specification<Pet> byUserId(UUID id) {
        return (Root<Pet> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(Pet_.user), id);
        };
    }
}
