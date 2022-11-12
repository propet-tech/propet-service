package br.com.senai.propetservice.data;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserDto implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;

}
