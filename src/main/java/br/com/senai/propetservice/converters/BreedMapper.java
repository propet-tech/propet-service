package br.com.senai.propetservice.converters;

import org.mapstruct.Mapper;

import br.com.senai.propetservice.data.BreedDto;
import br.com.senai.propetservice.models.PetBreed;

@Mapper
public interface BreedMapper {

    BreedDto map(PetBreed entity);

    PetBreed map(BreedDto entity);
}
