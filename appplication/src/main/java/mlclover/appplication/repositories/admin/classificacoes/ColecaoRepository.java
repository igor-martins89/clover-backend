package mlclover.appplication.repositories.admin.classificacoes;

import mlclover.appplication.entities.admin.classificacoes.Colecao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColecaoRepository extends JpaRepository<Colecao, Integer> {

    Colecao findByNome(String nome);
}
