package br.com.senai.propetservice.converters;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import br.com.senai.propetservice.controller.PetRest;
import br.com.senai.propetservice.data.request.PetRequestDto;
import br.com.senai.propetservice.data.response.PetResponse;
import br.com.senai.propetservice.models.Pet;

@Mapper(componentModel = ComponentModel.SPRING)
public abstract class PetMapper {

    @Mappings({
            @Mapping(target = "image", ignore = true),
    })
    public abstract PetResponse map(Pet pet);

    @Mappings({
            @Mapping(target = "image", ignore = true),
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "breed.id", source = "pet.breedId")
    })
    public abstract Pet map(PetRequestDto pet);

    @BeforeMapping
    protected void createImageUrl(@MappingTarget PetResponse dto, Pet pet) {
        final var image = MvcUriComponentsBuilder
                .fromMethodCall(MvcUriComponentsBuilder.on(PetRest.class)
                        .getPetImage(pet.getId()))
                .toUriString();

        if (pet.getImage() != null)
            dto.setImage(image);
    }

}
