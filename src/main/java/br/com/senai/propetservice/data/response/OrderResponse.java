package br.com.senai.propetservice.data.response;

import java.io.Serializable;
import java.util.UUID;

import br.com.senai.propetservice.data.PetShopServiceDto;
import lombok.Data;

@Data
public class OrderResponse implements Serializable {

    private Long id;
    private PetResponse pet;
    private UUID user;
    private PetShopServiceDto service;
    private String notes;
}
