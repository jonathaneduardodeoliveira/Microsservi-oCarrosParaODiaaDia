package servico.client.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import servico.client.dto.ClientDTO;
import servico.client.service.ClientService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ClientControllerTest {

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateClient() {
        ClientDTO clientDTO = new ClientDTO(null, "Jonathan Oliveira", "123456789", null);

        when(clientService.createClient(any(ClientDTO.class))).thenReturn(clientDTO);

        ResponseEntity<ClientDTO> response = clientController.createClient(clientDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(clientDTO, response.getBody());
        verify(clientService, times(1)).createClient(any(ClientDTO.class));
    }

    @Test
    void testGetClientById() {
        ClientDTO clientDTO = new ClientDTO(1L, "Jonathan Oliveira", "123456789", "habilitacao123");

        when(clientService.getClientById(1L)).thenReturn(clientDTO);

        ResponseEntity<ClientDTO> response = clientController.getClientById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientDTO, response.getBody());
        verify(clientService, times(1)).getClientById(1L);
    }
}
