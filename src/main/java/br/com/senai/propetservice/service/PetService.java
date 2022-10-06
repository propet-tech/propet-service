package br.com.senai.propetservice.service;

import br.com.senai.propetservice.converters.ModelToDto;
import br.com.senai.propetservice.data.PetDto;
import br.com.senai.propetservice.data.UserDto;
import br.com.senai.propetservice.models.Pet;
import br.com.senai.propetservice.repository.PetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    @Autowired
    private PetRepo respositoy;

    public UserDto getPetOwner(Long petId) {
        return ModelToDto.parseObject(respositoy.findById(petId).get().getUser(), UserDto.class);
    }

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
}
