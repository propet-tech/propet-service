package br.com.senai.propetservice.data.request;

import java.io.Serializable;
import java.util.UUID;

import br.com.senai.propetservice.models.enums.PetCategory;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PetRequestDto implements Serializable {

    private String name;
    private UUID userId;
    private Long breedId;
    private PetCategory category;
    private Float weight;
    private String description;

}
