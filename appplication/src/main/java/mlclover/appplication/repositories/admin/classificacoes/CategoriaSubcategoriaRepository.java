package mlclover.appplication.repositories.admin.classificacoes;

import mlclover.appplication.entities.admin.classificacoes.CategoriaSubcategoria;
import mlclover.appplication.entities.admin.classificacoes.ids.CategoriaSubcategoriaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaSubcategoriaRepository extends JpaRepository<CategoriaSubcategoria, CategoriaSubcategoriaId> {
}
