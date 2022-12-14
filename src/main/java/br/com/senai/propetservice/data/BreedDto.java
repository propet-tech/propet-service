package br.com.senai.propetservice.data;

import java.io.Serializable;

import br.com.senai.propetservice.models.enums.PetType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BreedDto implements Serializable {
   
    private Long id;
    private PetType type;
    private String name;
}
