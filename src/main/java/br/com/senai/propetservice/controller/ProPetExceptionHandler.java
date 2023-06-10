package br.com.senai.propetservice.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.senai.propetservice.data.ExceptionDto;
import br.com.senai.propetservice.models.exceptions.FileStorageException;
import br.com.senai.propetservice.models.exceptions.NotFoundException;

@RestController
@ControllerAdvice
public class ProPetExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto> handlerNotFound(NotFoundException ex, WebRequest req) {
        ExceptionDto response = new ExceptionDto(
            LocalDateTime.now(),
            ex.getMessage(),
            req.getDescription(true)
        );

        return new ResponseEntity<ExceptionDto>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<ExceptionDto> handlerStorage(FileStorageException ex, WebRequest req) {
        ExceptionDto response = new ExceptionDto(
            LocalDateTime.now(),
            ex.getMessage(),
            req.getDescription(true)
        );

        return new ResponseEntity<ExceptionDto>(response, HttpStatus.BAD_REQUEST);
    }
}
