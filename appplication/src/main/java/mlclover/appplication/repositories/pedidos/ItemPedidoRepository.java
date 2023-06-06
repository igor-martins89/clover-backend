package mlclover.appplication.repositories.pedidos;

import mlclover.appplication.entities.pedidos.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
}
