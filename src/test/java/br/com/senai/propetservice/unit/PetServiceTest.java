package br.com.senai.propetservice.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import br.com.senai.propetservice.data.request.PetRequestDto;
// import br.com.senai.propetservice.models.User;
import br.com.senai.propetservice.models.Pet;
import br.com.senai.propetservice.repository.PetRepo;
import br.com.senai.propetservice.service.PetService;

@ExtendWith(MockitoExtension.class)
public class PetServiceTest {

    @InjectMocks
    private PetService service;

    @Mock
    private PetRepo repo;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        var dto = PetRequestDto.builder()
            .id(1L)
            .name("test1").build();

        ArgumentCaptor<Pet> captor = ArgumentCaptor.forClass(Pet.class);

        service.createPet(dto);
        Mockito.verify(repo).save(captor.capture());
        var pet = captor.getValue();
        
        assertEquals(1L, pet.getId());
        assertEquals("test1", pet.getName());
    }

    @Test
    void testGetPet() {
        var pet = Pet.builder()
            .id(1L)
            .name("test").build();

        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(pet));

        var result = service.getPet(1L);

        assertNotNull(result);
        assertEquals(result.getName(), "test");
    }

    @Test
    void testUpdatePet() {
        var dto = PetRequestDto.builder()
            .id(1L)
            .name("test1").build();        

        ArgumentCaptor<Pet> captor = ArgumentCaptor.forClass(Pet.class);

        service.updatePet(dto);
        Mockito.verify(repo).save(captor.capture());
        var client = captor.getValue();
        
        assertEquals(1L, client.getId());
        assertEquals("test1", client.getName());
    }

    @Test
    void testDeletePet() {
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        service.deletePet(1L);
        Mockito.verify(repo).deleteById(captor.capture());
        assertEquals(1L, captor.getValue());
    }

    @Test
    void testGetAllPets() {
        var pets = new ArrayList<Pet>();
        pets.add(
            Pet.builder()
                .id(1L)
            .name("test").build()
        );
        
        Pageable pageable = PageRequest.of(0, 2);
        Mockito.when(repo.findAll(pageable)).thenReturn(new PageImpl<>(pets));

        var result = service.getAllPets(pageable);

        assertNotNull(result);
        assertEquals(result.getTotalElements(), 1);
        assertEquals(result.getContent().get(0).getName(), "test");
    }

    // @Test
    // void testGetAllPetsByOwner() {
    //     var pets = new ArrayList<Pet>();
    //     var client = User.builder().id(1L).build();
    //
    //     pets.add(
    //         Pet.builder()
    //             .id(1L)
    //             .client(client)
    //         .name("test").build()
    //     );
    //     
    //     Pageable pageable = PageRequest.of(0, 2);
    //     Mockito.when(repo.getAllByClient(pageable, 1L)).thenReturn(new PageImpl<>(pets));
    //
    //     var result = service.getAllPetsByOwner(1L, pageable);
    //
    //     assertNotNull(result);
    //     assertEquals(result.getTotalElements(), 1);
    //     assertEquals(result.getContent().get(0).getName(), "test");
    //     assertEquals(result.getContent().get(0).getClientId(), 1L);
    // }
    //
}
