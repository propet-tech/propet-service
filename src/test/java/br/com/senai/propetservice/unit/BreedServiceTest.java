package br.com.senai.propetservice.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
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

import br.com.senai.propetservice.data.BreedDto;
import br.com.senai.propetservice.models.PetBreed;
import br.com.senai.propetservice.models.enums.PetType;
import br.com.senai.propetservice.repository.BreedRepo;
import br.com.senai.propetservice.service.BreedService;

@ExtendWith(MockitoExtension.class)
public class BreedServiceTest {

    @InjectMocks
    private BreedService service;

    @Mock
    private BreedRepo repo;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        var dto = BreedDto.builder()
            .id(1L)
            .type(PetType.CAT)
            .name("test1").build();

        ArgumentCaptor<PetBreed> captor = ArgumentCaptor.forClass(PetBreed.class);

        service.create(dto);
        Mockito.verify(repo).save(captor.capture());
        var breed = captor.getValue();
        
        assertEquals(1L, breed.getId());
        assertEquals("test1", breed.getName());
    }

    @Test
    void testGetById() {
        var breed = PetBreed.builder()
                .id(1L)
                .name("test")
            .build();

        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(breed));

        var result = service.getById(1L);

        assertNotNull(result);
        assertEquals(result.getName(), "test");
    }

    @Test void testDelete() {
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        service.delete(1L);
        Mockito.verify(repo).deleteById(captor.capture());
        assertEquals(1L, captor.getValue());
    }

    @Test
    void testGetAll() {
        List<PetBreed> breeds = new ArrayList<>();

        breeds.add(
            PetBreed.builder()
                .id(1L)
                .name("test")
            .build()
        );
        breeds.add(
            PetBreed.builder()
                .id(2L)
                .name("test2")
            .build()
        );

        Pageable pageable = PageRequest.of(0, 2);
        Mockito.when(repo.findAll(pageable)).thenReturn(new PageImpl<>(breeds));

        var result = service.getAll(pageable);

        assertNotNull(result);
        assertEquals(result.getTotalElements(), 2);
        assertEquals(result.getContent().get(1).getName(), "test2");
    }
}
