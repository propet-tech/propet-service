package br.com.senai.propetservice.service;

import br.com.senai.propetservice.converters.GenericMapper;
import br.com.senai.propetservice.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senai.propetservice.data.ClientDto;
import br.com.senai.propetservice.repository.ClientRepo;

@Service
public class ClientService {

    @Autowired
    private ClientRepo repository;

    public void create(ClientDto client) {
        repository.save(
                GenericMapper.parseObject(client, Client.class)
        );
    }

    public ClientDto getClientById(Long id) {
        var client = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Client Not Found")
        );

        return GenericMapper.parseObject(client, ClientDto.class);
    }

    public void updateClient(ClientDto client) {
        repository.save(
                GenericMapper.parseObject(client, Client.class)
        );
    }

    public void deleteClient(Long id) {
        repository.deleteById(id);
    }
}
