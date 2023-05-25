package mlclover.appplication.repositories.clientes;

import mlclover.appplication.entities.clientes.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    List<Endereco> findAllByClienteId(int idCliente);
}
