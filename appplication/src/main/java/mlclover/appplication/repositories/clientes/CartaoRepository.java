package mlclover.appplication.repositories.clientes;

import mlclover.appplication.entities.clientes.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Integer> {

    List<Cartao> findAllByClienteId(int idCliente);

}
