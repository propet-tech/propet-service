package br.com.senai.propetservice.service;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.senai.propetservice.converters.BreedMapper;
import br.com.senai.propetservice.data.BreedDto;
import br.com.senai.propetservice.models.exceptions.NotFoundException;
import br.com.senai.propetservice.repository.BreedRepo;

@Service
public class BreedService {
    
    @Autowired
    private BreedRepo repo;

    private BreedMapper mapper = Mappers.getMapper(BreedMapper.class);

    public void create(BreedDto dto) {
        repo.save(
            mapper.map(dto)
        );
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Page<BreedDto> getAll(Pageable pageable) {
        return repo.findAll(pageable).map(
            entity -> mapper.map(entity)
        );
    }

    public BreedDto getById(Long id) {
        return mapper.map(
            repo.findById(id).orElseThrow(
                () -> new NotFoundException("No Breed with this id find")
            )
        );
    }
}
