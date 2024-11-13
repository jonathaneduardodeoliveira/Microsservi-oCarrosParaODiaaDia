package servico.client.factory;

import servico.client.model.Client;
import java.time.LocalDateTime;

public class ClientFactory {

    public static Client createNewClient(String name, String cpf, String habilitacao) {
        return new Client(name, cpf, habilitacao, LocalDateTime.now());
    }
}
