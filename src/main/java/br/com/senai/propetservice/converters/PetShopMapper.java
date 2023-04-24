package br.com.senai.propetservice.converters;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import br.com.senai.propetservice.data.PetShopServiceDto;
import br.com.senai.propetservice.models.PetShopService;

@Mapper
public interface PetShopMapper {

    @Mappings({
        @Mapping(source = "petShopService.user.id", target = "userId"),
        @Mapping(source = "petShopService.pet.id", target = "petId")
    })
    PetShopServiceDto map(PetShopService petShopService);


    @Mappings({
        @Mapping(source = "dto.userId", target = "user.id"),
        @Mapping(source = "dto.petId", target = "pet.id")
    })
    PetShopService map(PetShopServiceDto dto);
}
