package br.com.senai.propetservice.service;

import jakarta.transaction.Transactional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import br.com.senai.propetservice.controller.HelloMessage;
import br.com.senai.propetservice.converters.PetShopMapper;
import br.com.senai.propetservice.data.PetShopServiceDto;
import br.com.senai.propetservice.models.enums.ServiceStatus;
import br.com.senai.propetservice.models.exceptions.NotFoundException;
import br.com.senai.propetservice.repository.PetShopServiceRepo;

@Service
public class PetShopService {
    
    @Autowired
    private PetShopServiceRepo petShopRepo;

    private PetShopMapper mapper = Mappers.getMapper(PetShopMapper.class);

    @Autowired
    private SimpMessagingTemplate template;

    @Transactional
    public void updateServiceStatus(Long id, ServiceStatus status) {
        petShopRepo.changeServiceStatus(id, status);
        var service = petShopRepo.findById(id).map(
            pet -> mapper.map(pet)
        ).get();
        template.convertAndSend("/topic/greetings", new HelloMessage<PetShopServiceDto>(service));
    }

    public Page<PetShopServiceDto> getAllActive(Pageable pageable) {
        return petShopRepo.findAllActive(pageable).map(
            petshop -> mapper.map(petshop)
        );
    }

    public Page<PetShopServiceDto> getAllServices(Pageable pageable) {
        return petShopRepo.findAll(pageable).map(
            petshop -> mapper.map(petshop)
        );
    }

    public void createService(PetShopServiceDto petShop) {
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

    public PetShopServiceDto getPetShopService(Long id) {
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

    public void updatePetShop(PetShopServiceDto service) {
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
