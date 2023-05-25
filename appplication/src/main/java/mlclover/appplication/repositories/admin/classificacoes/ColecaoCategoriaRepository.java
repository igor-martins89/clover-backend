package mlclover.appplication.repositories.admin.classificacoes;

import mlclover.appplication.entities.admin.classificacoes.ColecaoCategoria;
import mlclover.appplication.entities.admin.classificacoes.ids.ColecaoCategoriaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColecaoCategoriaRepository extends JpaRepository<ColecaoCategoria, ColecaoCategoriaId> {
}
