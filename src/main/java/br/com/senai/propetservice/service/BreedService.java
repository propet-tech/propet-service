package br.com.senai.propetservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.senai.propetservice.converters.GenericMapper;
import br.com.senai.propetservice.data.BreedDto;
import br.com.senai.propetservice.models.PetBreed;
import br.com.senai.propetservice.repository.BreedRepo;

@Service
public class BreedService {
    
    @Autowired
    private BreedRepo repo;

    public void create(BreedDto dto) {
        repo.save(
            GenericMapper.parseObject(dto, PetBreed.class)
        );
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Page<BreedDto> getAll(Pageable pageable) {
        return repo.findAll(pageable).map(
            entity -> GenericMapper.parseObject(entity, BreedDto.class)
        );
    }
}
