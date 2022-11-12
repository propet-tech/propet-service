package br.com.senai.propetservice.data;

import java.io.Serializable;

import lombok.Data;

@Data
public class PetDto implements Serializable {

    private Long id;
    private String name;
    private Long userId;
    private Float weight;
    private String description;

}
