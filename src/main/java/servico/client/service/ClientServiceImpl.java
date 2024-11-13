package servico.client.service;

import servico.client.dto.ClientDTO;
import servico.client.model.Client;
import servico.client.repository.ClientRepository;
import servico.client.factory.ClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = ClientFactory.createNewClient(clientDTO.getName(), clientDTO.getCpf(), clientDTO.getHabilitacao());
        client = clientRepository.save(client);
        return new ClientDTO(client.getId(), client.getName(), client.getCpf(), client.getHabilitacao());
    }

    @Override
    public ClientDTO getClientById(Long id) {
        return clientRepository.findById(id)
                .map(client -> new ClientDTO(client.getId(), client.getName(), client.getCpf(), client.getHabilitacao()))
                .orElse(null);
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(client -> new ClientDTO(client.getId(), client.getName(), client.getCpf(), client.getHabilitacao()))
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
        return clientRepository.findById(id).map(client -> {
            client.setName(clientDTO.getName());
            client.setCpf(clientDTO.getCpf());
            client.setHabilitacao(clientDTO.getHabilitacao());
            clientRepository.save(client);
            return new ClientDTO(client.getId(), client.getName(), client.getCpf(), client.getHabilitacao());
        }).orElse(null);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
