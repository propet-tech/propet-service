package br.com.senai.propetservice.converters;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import br.com.senai.propetservice.data.PetDto;
import br.com.senai.propetservice.models.Pet;

@Mapper
public interface PetMapper {

    @Mappings({
        @Mapping(target = "clientId", source = "pet.client.id")
    })
    PetDto map(Pet pet);

    @Mappings({
        @Mapping(target = "client.id", source = "pet.clientId")
    })
    Pet map(PetDto pet);

}
