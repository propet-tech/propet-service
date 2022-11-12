package br.com.senai.propetservice.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "petshop")
public class PetShop implements Serializable {
    
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

}
