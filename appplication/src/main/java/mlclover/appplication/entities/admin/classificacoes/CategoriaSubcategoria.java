package mlclover.appplication.entities.admin.classificacoes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mlclover.appplication.entities.admin.classificacoes.ids.CategoriaSubcategoriaId;
import mlclover.appplication.entities.admin.produtos.Produto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_categoria_subcategoria")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoriaSubcategoria {

    @EmbeddedId
    CategoriaSubcategoriaId id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "colecao_id", referencedColumnName = "colecao_id", insertable = false, updatable = false),
            @JoinColumn(name = "categoria_id", referencedColumnName = "categoria_id", insertable = false, updatable = false)
    })
    private ColecaoCategoria colecaoCategoriaId;

    @ManyToOne
    @JoinColumn(name = "subcategoria_id", insertable = false, updatable = false)
    private Subcategoria subcategoriaId;

    @OneToMany(mappedBy = "categoriaSubcategoriaId")
    private List<ProdutoSubcategoria> produtos = new ArrayList<>();

    public CategoriaSubcategoria(CategoriaSubcategoriaId id, ColecaoCategoria colecaoCategoriaId, Subcategoria subcategoriaId) {
        this.id = id;
        this.colecaoCategoriaId = colecaoCategoriaId;
        this.subcategoriaId = subcategoriaId;
    }
}
