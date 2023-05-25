package mlclover.appplication.repositories.clientes;

import mlclover.appplication.entities.clientes.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    /** Deixar mais rápido e diminui o locking no gerenciamento de transações do Banco de Dados */
    @Transactional
    Optional<Cliente> findByEmail(String email);

    boolean findIsLogadoById(int id);
}
