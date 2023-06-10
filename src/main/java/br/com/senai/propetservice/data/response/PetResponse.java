package br.com.senai.propetservice.data.response;

import java.io.Serializable;
import java.util.UUID;

import br.com.senai.propetservice.models.PetBreed;
import lombok.Data;

@Data
public class PetResponse implements Serializable {
    private Long id;
    private String name;
    private String image;
    private UUID user;
    private PetBreed breed;
    private Float weight;
    private String description;

}
