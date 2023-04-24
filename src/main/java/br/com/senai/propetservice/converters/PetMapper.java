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
        @Mapping(target = "userId", source = "pet.user.id")
    })
    PetResponseDto map(Pet pet);

    @Mappings({
        @Mapping(target = "user.id", source = "pet.userId"),
        @Mapping(target = "breed.id", source = "pet.breedId")
    })
    Pet map(PetRequestDto pet);

}
