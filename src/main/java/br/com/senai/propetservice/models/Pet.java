package br.com.senai.propetservice.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pets")
public class Pet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    private User user;

    @Column
    private Float weight;

    @Column
    private String description;
}
