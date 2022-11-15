package br.com.senai.propetservice.data;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PetShopDto implements Serializable {

    private Long id;
    private Long clientId;
    private Long petId;
    private String description;
    private Boolean active;
    private LocalDateTime startDateTime;
    private LocalDateTime doneDateTime;
}
