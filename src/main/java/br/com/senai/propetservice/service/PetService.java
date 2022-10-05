package br.com.senai.propetservice.service;

import br.com.senai.propetservice.repository.PetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    @Autowired
    private PetRepo respositoy;


}
