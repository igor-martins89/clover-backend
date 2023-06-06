package mlclover.appplication.repositories.pedidos;

import mlclover.appplication.entities.pedidos.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
