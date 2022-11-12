package br.com.senai.propetservice.converters;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import br.com.senai.propetservice.data.PetDto;
import br.com.senai.propetservice.models.Pet;

@Mapper
public interface PetMapper {

    @Mappings({
        @Mapping(target = "userId", source = "pet.user.id")
    })
    PetDto map(Pet pet);

    @Mappings({
        @Mapping(target = "user.id", source = "pet.userId")
    })
    Pet map(PetDto pet);

}
