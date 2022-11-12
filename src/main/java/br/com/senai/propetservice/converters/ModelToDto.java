package br.com.senai.propetservice.converters;

import org.modelmapper.ModelMapper;

import br.com.senai.propetservice.data.PetDto;
import br.com.senai.propetservice.data.PetShopDto;
import br.com.senai.propetservice.models.Pet;
import br.com.senai.propetservice.models.PetShop;

public class ModelToDto {
    private static final ModelMapper mapper = new ModelMapper();

    public static <S, D> D parseObject(S source, Class<D> destination) {
        // for (Field field : destination.getFields()) {
        //     
        //     if (field.isAnnotationPresent(Id.class));
        // }
        return mapper.map(source, destination);
    }

    public static PetDto parsePet(Pet source) {
        mapper.typeMap(Pet.class, PetDto.class).addMappings(
            mapper -> {
                mapper.map(
                    pet -> pet.getUser().getId(),
                    PetDto::setUserId
                );
            }
        );
        
        return mapper.map(source, PetDto.class);
    }

    public static Pet parsePet(PetDto source) {
        mapper.typeMap(PetDto.class, Pet.class).addMappings(
            mapper -> {
                mapper.map(
                    petDto -> petDto.getUserId(),
                    Pet::setUser
                );
            }
        );
        return mapper.map(source, Pet.class);
    }


    public static PetShopDto parsePetShop(PetShop source) {
        mapper.typeMap(PetShop.class, PetShopDto.class).addMappings(
            mapper -> {
                mapper.map(
                    petshop -> petshop.getUser().getId(),
                    PetShopDto::setUserId
                );
            }
        );

        mapper.typeMap(PetShop.class, PetShopDto.class).addMappings(
            mapper -> {
                mapper.map(
                    petshop -> petshop.getPet().getId(), 
                    PetShopDto::setPetId
                );
            }
        );

        return mapper.map(source, PetShopDto.class);
    }

    public static PetShop parsePetShop(PetShopDto source) {
        mapper.typeMap(PetShopDto.class, PetShop.class).addMappings(
            mapper -> {
                mapper.map(
                    petshop -> petshop.getUserId(),
                    PetShop::setUser
                );
            }
        );

        mapper.typeMap(PetShopDto.class, PetShop.class).addMappings(
            mapper -> {
                mapper.map(
                    petshop -> petshop.getPetId(), 
                    PetShop::setPet
                );
            }
        );

        return mapper.map(source, PetShop.class);
    }

}
