package br.com.senai.propetservice.unit;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
//
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;
//
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.ArgumentCaptor;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.mockito.MockitoAnnotations;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.data.domain.PageImpl;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
//
// import br.com.senai.propetservice.data.ClientDto;
// import br.com.senai.propetservice.models.User;
// import br.com.senai.propetservice.repository.ClientRepo;
// import br.com.senai.propetservice.service.ClientService;
//
// @ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    // @InjectMocks
    // private ClientService service;
    //
    // @Mock
    // private ClientRepo repo;
    //
    // @BeforeEach
    // void setUp() throws Exception {
    //     MockitoAnnotations.openMocks(this);
    // }
    //
    // @Test
    // void testCreate() {
    //     var dto = ClientDto.builder()
    //         .id(1L)
    //         .name("test1").build();
    //
    //     ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
    //
    //     service.create(dto);
    //     Mockito.verify(repo).save(captor.capture());
    //     var client = captor.getValue();
    //     
    //     assertEquals(1L, client.getId());
    //     assertEquals("test1", client.getName());
    // }
    //
    // @Test
    // void testGetClientById() {
    //     var client = User.builder()
    //             .id(1L)
    //             .name("test")
    //         .build();
    //
    //     Mockito.when(repo.findById(1L)).thenReturn(Optional.of(client));
    //
    //     var result = service.getClientById(1L);
    //
    //     assertNotNull(result);
    //     assertEquals(result.getName(), "test");
    // }
    //
    // @Test
    // void testUpdateClient() {
    //     var dto = ClientDto.builder()
    //         .id(1L)
    //         .name("test1").build();
    //
    //     ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
    //
    //     service.updateClient(dto);
    //     Mockito.verify(repo).save(captor.capture());
    //     var client = captor.getValue();
    //     
    //     assertEquals(1L, client.getId());
    //     assertEquals("test1", client.getName());
    // }
    //
    // @Test void testDeleteClient() {
    //     ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
    //     service.deleteClient(1L);
    //     Mockito.verify(repo).deleteById(captor.capture());
    //     assertEquals(1L, captor.getValue());
    // }
    //
    // @Test
    // void testGetAllClients() {
    //     List<User> clients = new ArrayList<>();
    //
    //     clients.add(
    //         User.builder()
    //             .id(1L)
    //             .name("test")
    //         .build()
    //     );
    //     clients.add(
    //         User.builder()
    //             .id(2L)
    //             .name("test2")
    //         .build()
    //     );
    //
    //     Pageable pageable = PageRequest.of(0, 2);
    //     Mockito.when(repo.findAll(pageable)).thenReturn(new PageImpl<>(clients));
    //
    //     var result = service.getAllClients(pageable);
    //
    //     assertNotNull(result);
    //     assertEquals(result.getTotalElements(), 2);
    //     assertEquals(result.getContent().get(1).getName(), "test2");
    // }
}
