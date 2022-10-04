package br.com.senai.propetservice.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pet_breeds")
public class PetBreed implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
