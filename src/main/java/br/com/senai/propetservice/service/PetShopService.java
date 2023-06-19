package br.com.senai.propetservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.senai.propetservice.converters.PetShopMapper;
import br.com.senai.propetservice.data.PetShopServiceDto;
import br.com.senai.propetservice.models.PetShopServicesView;
import br.com.senai.propetservice.models.exceptions.NotFoundException;
import br.com.senai.propetservice.repository.PetShopServiceRepo;
import br.com.senai.propetservice.repository.PetShopServicesViewRepo;

@Service
public class PetShopService {

    @Autowired
    private PetShopServiceRepo petShopRepo;

    @Autowired
    private PetShopMapper mapper;

    @Autowired
    private PetShopServicesViewRepo petShopRepoView;

    public List<PetShopServicesView> teste() {
        return petShopRepoView.findAll(Sort.by(Sort.Direction.DESC, "links"));
    }

    public Page<PetShopServiceDto> getAllServices(String search, Pageable pageable) {
        if (search == null || search.isBlank() || search.isEmpty()) {
            return petShopRepo
                .findAll(pageable).map(petshop -> mapper.map(petshop));
        } else {
            String[] array = {"name", "description"};
            return petShopRepo.searchBy(search, array, pageable)
                .map(petshop -> mapper.map(petshop));
        }
    }

    public void createService(PetShopServiceDto petShop) {
        petShop.setId(null);
        petShopRepo.save(
                mapper.map(petShop));
    }

    public void deleteService(Long id) {
        if (!petShopRepo.existsById(id)) {
            throw new NotFoundException(
                    String.format("PetShop sevice '%d' not found", id));
        }

        petShopRepo.deleteById(id);
    }

    public PetShopServiceDto getPetShopService(Long id) {
        return mapper.map(
                petShopRepo.findById(id).orElseThrow(
                        () -> new NotFoundException(
                                String.format("PetShop sevice '%d' not found", id))));
    }

    public Long getNumberOfPetShop() {
        return petShopRepo.count();
    }

    public void updatePetShop(PetShopServiceDto service) {
        if (!petShopRepo.existsById(service.getId())) {
            throw new NotFoundException(
                    String.format("PetShop sevice id: %d not found", service.getId()));
        }

        petShopRepo.save(
                mapper.map(service));
    }
}
