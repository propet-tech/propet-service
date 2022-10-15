package br.com.senai.propetservice.service;

import br.com.senai.propetservice.converters.ModelToDto;
import br.com.senai.propetservice.data.PetDto;
import br.com.senai.propetservice.models.Pet;
import br.com.senai.propetservice.repository.PetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    @Autowired
    private PetRepo respositoy;

    public void createPet(PetDto pet) {
        respositoy.save(
                ModelToDto.parseObject(pet, Pet.class)
        );
    }

    public PetDto getPet(Long id) {
        Pet pet = respositoy.findById(id).orElseThrow(
                () -> new RuntimeException("Pet Not Found")
        );
        return ModelToDto.parseObject(pet, PetDto.class);
    }

    public Page<PetDto> getAllPets(Pageable pageable) {
        return respositoy.findAll(pageable).map(
                pet -> ModelToDto.parseObject(pet, PetDto.class)
        );
    }

    public void deletePet(Long id) {
        respositoy.deleteById(id);
    }

    public void updatePet(PetDto pet) {
        respositoy.save(
                ModelToDto.parseObject(pet, Pet.class)
        );
    }
}
