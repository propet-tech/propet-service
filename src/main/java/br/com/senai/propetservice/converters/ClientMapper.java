package br.com.senai.propetservice.converters;

import org.mapstruct.Mapper;

import br.com.senai.propetservice.data.ClientDto;
import br.com.senai.propetservice.models.Client;

@Mapper
public interface ClientMapper {

    Client map(ClientDto dto);

    ClientDto map(Client entity);
}
