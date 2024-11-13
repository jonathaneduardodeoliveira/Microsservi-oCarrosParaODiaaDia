package servico.client.service;

import servico.client.dto.ClientDTO;
import servico.client.model.Client;

import java.util.List;

public interface ClientService {
    ClientDTO createClient(ClientDTO clientDTO);
    ClientDTO getClientById(Long id);
    List<ClientDTO> getAllClients();
    ClientDTO updateClient(Long id, ClientDTO clientDTO);
    void deleteClient(Long id);
}
