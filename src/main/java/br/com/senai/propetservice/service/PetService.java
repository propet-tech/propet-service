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
    private PetRepo repository;

    public void createPet(PetDto pet) {
        repository.save(
                ModelToDto.parseObject(pet, Pet.class)
        );
    }

    public PetDto getPet(Long id) {
        Pet pet = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Pet Not Found")
        );
        return ModelToDto.parseObject(pet, PetDto.class);
    }

    public Page<PetDto> getAllPets(Pageable pageable) {
        return repository.findAll(pageable).map(
                pet -> ModelToDto.parseObject(pet, PetDto.class)
        );
    }

    public Page<PetDto> getAllPetsByOwner(Long userId, Pageable pageable) {
        return repository.getAllByUser(pageable, userId).map(
            pet -> ModelToDto.parseObject(pet, PetDto.class)
        );
    }

    public void deletePet(Long id) {
        repository.deleteById(id);
    }

    public void updatePet(PetDto pet) {
        repository.save(
                ModelToDto.parseObject(pet, Pet.class)
        );
    }

    public Long getNumberOfPets() {
        return repository.count();
    }
    
    public Long getNumberOfPetsByUser(Long userId) {
       return repository.countByUser(userId); 
    }
}
