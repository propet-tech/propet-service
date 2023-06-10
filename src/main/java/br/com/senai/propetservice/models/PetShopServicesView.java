package br.com.senai.propetservice.models;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Immutable
@Table(name = "petshop_services_view")
public class PetShopServicesView {

    @Id
    @Column(name = "service_id")
    private Long serviceId;

    @OneToOne
    @JoinColumn(name = "service_id")
    private PetShopService service;

    @Column(name = "links")
    private Long links;
}
