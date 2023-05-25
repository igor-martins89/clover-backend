package mlclover.appplication.repositories.admin.classificacoes;

import mlclover.appplication.entities.admin.classificacoes.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    Categoria findByNome(String nome);
}
