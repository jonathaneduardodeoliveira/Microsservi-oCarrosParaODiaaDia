package servico.client.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import servico.client.dto.ClientDTO;
import servico.client.factory.ClientFactory;
import servico.client.model.Client;
import servico.client.repository.ClientRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ClientServiceImplTest {

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateClient() {
        ClientDTO clientDTO = new ClientDTO(null, "Jonathan Oliveira", "123456789", null);

        Client client = new Client("Jonathan Oliveira", "123456789", null, null);

        when(clientRepository.save(any(Client.class))).thenReturn(client);

        ClientDTO result = clientService.createClient(clientDTO);

        assertEquals(clientDTO.getName(), result.getName());
        assertEquals(clientDTO.getCpf(), result.getCpf());
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void testGetClientById() {
        Client client = new Client("Jonathan Oliveira", "123456789", "habilitacao123", null);
        client.setId(1L);

        ClientDTO expectedDTO = new ClientDTO(1L, "Jonathan Oliveira", "123456789", "habilitacao123");

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        ClientDTO result = clientService.getClientById(1L);

        assertEquals(expectedDTO.getName(), result.getName());
        assertEquals(expectedDTO.getCpf(), result.getCpf());
        assertEquals(expectedDTO.getHabilitacao(), result.getHabilitacao());
        verify(clientRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateClient() {
        Client existingClient = new Client("Jonathan Oliveira", "123456789", "habilitacao123", null);
        existingClient.setId(1L);

        ClientDTO updatedDTO = new ClientDTO(null, "Jonathan Updated", "987654321", "habilitacao456");

        when(clientRepository.findById(1L)).thenReturn(Optional.of(existingClient));
        when(clientRepository.save(any(Client.class))).thenReturn(existingClient);

        ClientDTO result = clientService.updateClient(1L, updatedDTO);

        assertEquals(updatedDTO.getName(), result.getName());
        assertEquals(updatedDTO.getCpf(), result.getCpf());
        assertEquals(updatedDTO.getHabilitacao(), result.getHabilitacao());
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void testDeleteClient() {
        // Executa o método a ser testado
        clientService.deleteClient(1L);

        verify(clientRepository, times(1)).deleteById(1L);
    }
}
