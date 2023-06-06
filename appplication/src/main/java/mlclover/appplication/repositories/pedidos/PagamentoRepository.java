package mlclover.appplication.repositories.pedidos;

import mlclover.appplication.entities.pedidos.abstracts.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}
