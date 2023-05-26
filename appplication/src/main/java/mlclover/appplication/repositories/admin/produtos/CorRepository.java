package mlclover.appplication.repositories.admin.produtos;

import mlclover.appplication.entities.admin.produtos.Cor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorRepository extends JpaRepository<Cor, String> {
}
