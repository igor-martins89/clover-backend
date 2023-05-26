package mlclover.appplication.repositories.admin.produtos;

import mlclover.appplication.entities.admin.produtos.CorProduto;
import mlclover.appplication.entities.admin.produtos.ids.CorProdutoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorProdutoRepository extends JpaRepository<CorProduto, CorProdutoId>{
}
