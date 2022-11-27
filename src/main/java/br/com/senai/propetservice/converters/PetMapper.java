package br.com.senai.propetservice.converters;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import br.com.senai.propetservice.data.request.PetRequestDto;
import br.com.senai.propetservice.data.response.PetResponseDto;
import br.com.senai.propetservice.models.Pet;

@Mapper
public interface PetMapper {

    @Mappings({
        @Mapping(target = "clientId", source = "pet.client.id")
    })
    PetResponseDto map(Pet pet);

    @Mappings({
        @Mapping(target = "client.id", source = "pet.clientId"),
        @Mapping(target = "breed.id", source = "pet.breedId")
    })
    Pet map(PetRequestDto pet);

}
