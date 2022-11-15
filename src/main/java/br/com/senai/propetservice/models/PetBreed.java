package br.com.senai.propetservice.models;

import javax.persistence.*;

import br.com.senai.propetservice.models.enums.PetType;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "pet_breeds")
public class PetBreed implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PetType type;

    @Column(nullable = false)
    private String name;
}
