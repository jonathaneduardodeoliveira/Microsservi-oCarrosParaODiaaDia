package servico.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import servico.client.model.Client;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByCpf(String cpf);
}
