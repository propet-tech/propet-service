package br.com.senai.propetservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.senai.propetservice.data.PetShopServicingDto;

@Service
public class PetShopService {
    
    public Page<PetShopServicingDto> getAllServices(Pageable pageable) {
        return null;
    }
}
