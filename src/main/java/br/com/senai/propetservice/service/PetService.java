package br.com.senai.propetservice.service;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.senai.propetservice.converters.PetMapper;
import br.com.senai.propetservice.data.request.PetRequestDto;
import br.com.senai.propetservice.data.response.PetResponseDto;
import br.com.senai.propetservice.models.Pet;
import br.com.senai.propetservice.repository.ClientRepo;
import br.com.senai.propetservice.repository.PetRepo;

@Service
public class PetService {

    @Autowired
    private PetRepo repository;

    @Autowired
    private ClientRepo clientRepo;

    private PetMapper mapper = Mappers.getMapper(PetMapper.class);

    public void createPet(PetRequestDto pet) {
        repository.save(
            mapper.map(pet)
        );
    }

    public PetResponseDto getPet(Long id) {
        Pet pet = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Pet Not Found")
        );
        return mapper.map(pet);
    }

    public Page<PetResponseDto> getAllPets(Pageable pageable) {
        return repository.findAll(pageable).map(
            pet -> mapper.map(pet)
        );
    }

    public Page<PetResponseDto> getAllPetsByOwner(Long clientId, Pageable pageable) {
        return repository.getAllByClient(pageable, clientId).map(
            pet -> mapper.map(pet)
        );
    }

    public void deletePet(Long id) {
        repository.deleteById(id);
    }

    public void updatePet(PetRequestDto pet) {
        repository.save(
            mapper.map(pet)
        );
    }

    public Long getNumberOfPets() {
        return repository.count();
    }
    
    public Long getNumberOfPetsByClient(Long clientId) {
        if (clientRepo.existsById(clientId))
            return repository.countByClient(clientId); 
        else
            throw new RuntimeException("Client not found");
    }
}
