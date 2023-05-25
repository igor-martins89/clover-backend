package mlclover.appplication.entities.admin.produtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mlclover.appplication.entities.admin.classificacoes.Categoria;
import mlclover.appplication.entities.admin.classificacoes.Colecao;
import mlclover.appplication.entities.admin.produtos.ids.CorProdutoId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_cor_produto")
public class CorProduto implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    CorProdutoId id;

    @ManyToOne
    @JoinColumn(name = "cor_id", insertable = false, updatable = false)
    private Cor corId;

    @ManyToOne
    @JoinColumn(name = "produto_id", insertable = false, updatable = false)
    private Produto produtoId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "TB_IMAGEM", joinColumns = {
            @JoinColumn(name = "produto_id", referencedColumnName = "produto_id"),
            @JoinColumn(name = "cor_id", referencedColumnName = "cor_id")
    })
    @Column(name = "imagem")
    private Set<String> imagens = new HashSet<>();

    public void addImagem(String imagem){
        imagens.add(imagem);
    }

}
