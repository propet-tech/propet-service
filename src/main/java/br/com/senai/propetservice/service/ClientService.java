package br.com.senai.propetservice.service;

// import org.mapstruct.factory.Mappers;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.stereotype.Service;
//
// import br.com.senai.propetservice.converters.ClientMapper;
// import br.com.senai.propetservice.data.ClientDto;
// import br.com.senai.propetservice.models.exceptions.NotFoundException;
// import br.com.senai.propetservice.repository.ClientRepo;

// @Service
public class ClientService {

    // @Autowired
    // private ClientRepo repository;
    //
    // private ClientMapper mapper = Mappers.getMapper(ClientMapper.class);
    //
    // public void create(ClientDto client) {
    //     repository.save(
    //         mapper.map(client)
    //     );
    // }
    //
    // public ClientDto getClientById(Long id) {
    //     var client = repository.findById(id).orElseThrow(
    //         () -> new NotFoundException("Client Not Found")
    //     );
    //
    //     return mapper.map(client);
    // }
    //
    // public void updateClient(ClientDto client) {
    //     repository.save(
    //         mapper.map(client)
    //     );
    // }
    //
    // public void deleteClient(Long id) {
    //     repository.deleteById(id);
    // }
    //
    // public Page<ClientDto> getAllClients(Pageable pageable) {
    //     return repository.findAll(pageable).map(
    //         client -> mapper.map(client)
    //     );
    // }
    //
    // public long count() {
    //     return repository.count();
    // }
}
