package br.com.senai.propetservice.data;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionDto implements Serializable {
   
    private LocalDateTime timestamp;
    private String message;
    private String details;
}
