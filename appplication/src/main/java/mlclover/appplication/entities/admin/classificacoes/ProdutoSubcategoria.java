package mlclover.appplication.entities.admin.classificacoes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mlclover.appplication.entities.admin.classificacoes.ids.ProdutoSubcategoriaId;
import mlclover.appplication.entities.admin.produtos.Produto;

import javax.persistence.*;

@Entity
@Table(name = "tb_produto_subcategoria")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProdutoSubcategoria {

    @EmbeddedId
    ProdutoSubcategoriaId id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "colecao_id", referencedColumnName = "colecao_id", insertable = false, updatable = false),
            @JoinColumn(name = "categoria_id", referencedColumnName = "categoria_id", insertable = false, updatable = false),
            @JoinColumn(name = "subcategoria_id", referencedColumnName = "subcategoria_id", insertable = false, updatable = false)
    })
    private CategoriaSubcategoria categoriaSubcategoriaId;

    @ManyToOne
    @JoinColumn(name = "produto_id", insertable = false, updatable = false)
    private Produto produtoId;
}
