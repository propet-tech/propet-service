package br.com.senai.propetservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.senai.propetservice.converters.ModelToDto;
import br.com.senai.propetservice.data.PetShopDto;
import br.com.senai.propetservice.models.PetShop;
import br.com.senai.propetservice.repository.PetShopRepo;

@Service
public class PetShopService {
    
    @Autowired
    private PetShopRepo petShopRepo;

    public Page<PetShopDto> getAllServices(Pageable pageable) {
        return petShopRepo.findAll(pageable).map(
            petshop -> ModelToDto.parsePetShop(petshop)
        );
    }

    public void createService(PetShopDto petShop) {
        petShopRepo.save(
            ModelToDto.parsePetShop(petShop)
        );
    }

    public void deleteService(Long id) {
        petShopRepo.deleteById(id);
    }

    public PetShopDto getPetShopService(Long id) {
        return ModelToDto.parseObject(
            petShopRepo.findById(id).orElseThrow(
                () -> new RuntimeException("PetShop not found")
            ),
            PetShopDto.class
        );
    }

    public Long getNumberOfPetShop() {
        return petShopRepo.count();
    }

    public void updatePetShop(PetShopDto service) {
        petShopRepo.save(
            ModelToDto.parsePetShop(service)
        );
    }
}
