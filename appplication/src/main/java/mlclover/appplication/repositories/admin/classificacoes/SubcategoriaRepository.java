package mlclover.appplication.repositories.admin.classificacoes;

import mlclover.appplication.entities.admin.classificacoes.Subcategoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubcategoriaRepository extends JpaRepository<Subcategoria, Integer> {
    Subcategoria findByNome(String nome);
}
