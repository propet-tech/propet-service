package br.com.senai.propetservice.repository.filters;

import org.springframework.data.jpa.domain.Specification;

import br.com.senai.propetservice.models.PetShopOrderHistory;
import br.com.senai.propetservice.models.PetShopOrderHistory_;
import br.com.senai.propetservice.models.PetShopServiceOrder_;

public class OrderFilters {

    public Specification<PetShopOrderHistory> filterByOrderId(Long id) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(PetShopOrderHistory_.petshopOrder)
                    .get(PetShopServiceOrder_.id), id);
        };
    }
}
