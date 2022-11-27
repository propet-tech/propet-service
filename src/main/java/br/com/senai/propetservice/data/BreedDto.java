package br.com.senai.propetservice.data;

import java.io.Serializable;

import br.com.senai.propetservice.models.enums.PetType;
import lombok.Data;

@Data
public class BreedDto implements Serializable {
   
    private Long id;
    private PetType type;
    private String name;
}
