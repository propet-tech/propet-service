package br.com.senai.propetservice.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HelloMessage<T> {

    private T content; 

}
