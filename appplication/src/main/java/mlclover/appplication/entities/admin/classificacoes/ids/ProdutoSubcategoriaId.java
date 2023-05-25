package mlclover.appplication.entities.admin.classificacoes.ids;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProdutoSubcategoriaId implements Serializable {

    @Column(name = "colecao_id")
    private Integer colecaoId;
    @Column(name = "categoria_id")
    private Integer categoriaId;
    @Column(name = "subcategoria_id")
    private Integer subcategoriaId;
    @Column(name = "produto_id")
    private Integer produtoId;
}
