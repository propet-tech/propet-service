package br.com.senai.propetservice.converters;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

public class ModelToDto {
    private static final ModelMapper mapper = new ModelMapper();

    public static <S, D> D parseObject(S source, Class<D> destination) {
        return mapper.map(source, destination);
    }
}
