package mlclover.appplication.repositories.admin.classificacoes;

import mlclover.appplication.entities.admin.classificacoes.ProdutoSubcategoria;
import mlclover.appplication.entities.admin.classificacoes.ids.ProdutoSubcategoriaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoSubcategoriaRepository extends JpaRepository<ProdutoSubcategoria, ProdutoSubcategoriaId> {
}
