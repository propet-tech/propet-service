package br.com.senai.propetservice.data;

import java.io.Serializable;

import lombok.Data;

@Data
public class PetShopDto implements Serializable {

    private Long id;
    private Long userId;
    private Long petId;
    private String description;
}
