package br.com.senai.propetservice.data;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDto implements Serializable {

    private Long id;
    private String name;
    private String cpf;
    private String email;

}
