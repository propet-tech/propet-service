package br.com.senai.propetservice.converters;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.MappingConstants.ComponentModel;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import br.com.senai.propetservice.controller.BannerRest;
import br.com.senai.propetservice.data.response.BannerResponseDto;
import br.com.senai.propetservice.models.Banner;

@Mapper(componentModel = ComponentModel.SPRING)
public abstract class BannerMapper {

    @Mappings({
        @Mapping(target = "image", ignore = true)
    })
    public abstract BannerResponseDto map(Banner entity);

    @BeforeMapping
    protected void createImageUrl(@MappingTarget BannerResponseDto dto, Banner banner) {
        final var image = MvcUriComponentsBuilder
                .fromMethodCall(MvcUriComponentsBuilder.on(BannerRest.class)
                        .getBannerImage(banner.getId()))
                .toUriString();

        dto.setImage(image);
    }
}
