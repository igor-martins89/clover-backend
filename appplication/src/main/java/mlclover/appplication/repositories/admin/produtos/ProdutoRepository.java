package mlclover.appplication.repositories.admin.produtos;

import mlclover.appplication.entities.admin.produtos.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    Page<Produto> findAll(Pageable paginacao);
    @Query("SELECT p FROM Produto p INNER JOIN p.produtoSubcategoriaId ps " +
            "WHERE ps.categoriaSubcategoriaId.id.colecaoId = :colecaoId")
    Page<Produto> findByColecaoId(@Param("colecaoId") Integer colecaoId,Pageable paginacao);

    @Query("SELECT p FROM Produto p INNER JOIN p.produtoSubcategoriaId ps " +
            "WHERE ps.categoriaSubcategoriaId.id.colecaoId = :colecaoId AND ps.categoriaSubcategoriaId.id.categoriaId = :categoriaId")
    Page<Produto> findByColecaoIdAndCategoriaId(@Param("colecaoId") Integer colecaoId, @Param("categoriaId") Integer categoriaId ,Pageable paginacao);

    @Query("SELECT p FROM Produto p INNER JOIN p.produtoSubcategoriaId ps " +
            "WHERE ps.categoriaSubcategoriaId.id.colecaoId = :colecaoId AND ps.categoriaSubcategoriaId.id.categoriaId = :categoriaId AND ps.categoriaSubcategoriaId.id.subcategoriaId = :subcategoriaId")
    Page<Produto> findByColecaoIdAndCategoriaIdAndSubcategoriaId(@Param("colecaoId") Integer colecaoId, @Param("categoriaId") Integer categoriaId, @Param("subcategoriaId") Integer subcategoriaId,Pageable paginacao);

    Page<Produto> findDistinctByNomeContainingIgnoreCase(String nomeProduto, Pageable paginacao);

    @Query("SELECT DISTINCT t FROM Produto p JOIN p.tamanhos t")
    Set<String> getAllTamanhos();

    List<Produto> findTop12ByOrderByIdDesc();
}
