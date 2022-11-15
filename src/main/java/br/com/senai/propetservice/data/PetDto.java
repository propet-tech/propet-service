package br.com.senai.propetservice.data;

import java.io.Serializable;

import br.com.senai.propetservice.models.PetBreed;
import br.com.senai.propetservice.models.enums.PetCategory;
import lombok.Data;

@Data
public class PetDto implements Serializable {

    private Long id;
    private String name;
    private Long clientId;
    private PetBreed breed;
    private PetCategory category;
    private Float weight;
    private String description;

}
