package br.com.senai.propetservice.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import br.com.senai.propetservice.models.enums.ServiceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "petshop")
public class PetShop implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    @OneToOne
    private Pet pet;

    @Column
    private String description;

    @Column(nullable = false)
    private Boolean active;

    @Column
    private LocalDateTime startDateTime;

    @Column
    private LocalDateTime doneDateTime;

    @Column
    @Enumerated(EnumType.STRING)
    private ServiceStatus status;
}
