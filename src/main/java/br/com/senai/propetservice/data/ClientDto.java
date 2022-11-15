package br.com.senai.propetservice.data;

import java.io.Serializable;

import lombok.Data;

@Data
public class ClientDto implements Serializable {

    private Long id;
    private String name;
    private String cpf;
    private String email;

}
