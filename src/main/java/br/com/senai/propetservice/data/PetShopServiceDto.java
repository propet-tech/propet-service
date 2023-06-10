package br.com.senai.propetservice.data;

import java.io.Serializable;

import lombok.Data;

@Data
public class PetShopServiceDto implements Serializable {

    private Long id;
    private String name;
    private float value;
    private String description;

}
