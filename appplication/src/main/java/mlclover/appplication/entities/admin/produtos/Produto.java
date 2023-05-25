package mlclover.appplication.entities.admin.produtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mlclover.appplication.entities.admin.classificacoes.CategoriaSubcategoria;
import mlclover.appplication.entities.admin.classificacoes.ColecaoCategoria;
import mlclover.appplication.entities.admin.classificacoes.ProdutoSubcategoria;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_produto")
public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable=false, length=60)
    private String nome;
    @Column(nullable=false, length=4096)
    private String descricao;
    @Column(nullable=false)
    private Double valor;

    @OneToMany(mappedBy = "produtoId")
    private List<ProdutoSubcategoria> produtoSubcategoriaId = new ArrayList<>();

    @OneToMany(mappedBy = "produtoId")
    private List<CorProduto> corProdutoId = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "TB_TAMANHO")
    @Column(name = "tamanho")
    private Set<String> tamanhos = new HashSet<>();

    public void addTamanho(String tamanho){
        tamanhos.add(tamanho);
    }

}
