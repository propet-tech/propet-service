package br.com.senai.propetservice.converters;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import br.com.senai.propetservice.data.PetShopDto;
import br.com.senai.propetservice.models.PetShop;

@Mapper
public interface PetShopMapper {

    @Mappings({
        @Mapping(source = "petshop.client.id", target = "clientId"),
        @Mapping(source = "petshop.pet.id", target = "petId")
    })
    PetShopDto map(PetShop petshop);


    @Mappings({
        @Mapping(source = "dto.clientId", target = "client.id"),
        @Mapping(source = "dto.petId", target = "pet.id")
    })
    PetShop map(PetShopDto dto);
}
