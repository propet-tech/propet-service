package br.com.senai.propetservice.data.request;

import java.io.Serializable;

import br.com.senai.propetservice.models.enums.PetCategory;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PetRequestDto implements Serializable {

    private Long id;
    private String name;
    private Long clientId;
    private Long breedId;
    private PetCategory category;
    private Float weight;
    private String description;

}
