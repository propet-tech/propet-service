package br.com.senai.propetservice.service;

import javax.transaction.Transactional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import br.com.senai.propetservice.controller.HelloMessage;
import br.com.senai.propetservice.converters.PetShopMapper;
import br.com.senai.propetservice.data.PetShopDto;
import br.com.senai.propetservice.models.enums.ServiceStatus;
import br.com.senai.propetservice.models.exceptions.NotFoundException;
import br.com.senai.propetservice.repository.PetShopRepo;

@Service
public class PetShopService {
    
    @Autowired
    private PetShopRepo petShopRepo;

    private PetShopMapper mapper = Mappers.getMapper(PetShopMapper.class);

    @Autowired
    private SimpMessagingTemplate template;

    @Transactional
    public void updateServiceStatus(Long id, ServiceStatus status) {
        petShopRepo.changeServiceStatus(id, status);
        var service = petShopRepo.findById(id).map(
            pet -> mapper.map(pet)
        ).get();
        template.convertAndSend("/topic/greetings", new HelloMessage<PetShopDto>(service));
    }

    public Page<PetShopDto> getAllActive(Pageable pageable) {
        return petShopRepo.findAllActive(pageable).map(
            petshop -> mapper.map(petshop)
        );
    }

    public Page<PetShopDto> getAllServices(Pageable pageable) {
        return petShopRepo.findAll(pageable).map(
            petshop -> mapper.map(petshop)
        );
    }

    public void createService(PetShopDto petShop) {
        petShop.setId(null);
        petShopRepo.save(
            mapper.map(petShop)
        );
    }

    public void deleteService(Long id) {
        if (!petShopRepo.existsById(id)){
            throw new NotFoundException( 
                String.format("PetShop sevice '%d' not found", id) 
            );
        }

        petShopRepo.deleteById(id);
    }

    public PetShopDto getPetShopService(Long id) {
        return mapper.map(
            petShopRepo.findById(id).orElseThrow(
                () -> new NotFoundException(
                    String.format("PetShop sevice '%d' not found", id)
                )
            )
        );
    }

    public Long getNumberOfPetShop() {
        return petShopRepo.count();
    }

    public void updatePetShop(PetShopDto service) {
        if (!petShopRepo.existsById(service.getId())) {
            throw new NotFoundException( 
                String.format("PetShop sevice id: %d not found", service.getId())
            );
        }

        petShopRepo.save(
            mapper.map(service)
        );
    }
}
