package br.com.senai.propetservice.models;

import java.time.LocalDateTime;

import br.com.senai.propetservice.models.enums.ServiceStatus;
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
@Table(name = "petshop_services")
public class PetShopService {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

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
