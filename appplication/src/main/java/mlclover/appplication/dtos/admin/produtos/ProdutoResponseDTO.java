package mlclover.appplication.dtos.admin.produtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mlclover.appplication.entities.admin.produtos.Produto;
import org.springframework.data.domain.Page;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProdutoResponseDTO {

    private Integer id;
    private String nome;
    private Double valor;
    private String descricao;
    private Set<String> tamanhos = new HashSet<>();

    public ProdutoResponseDTO(Produto produto) {
        this.id = produto.getId();;
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.descricao = produto.getDescricao();
        this.tamanhos = produto.getTamanhos();
    }

    public static Page<ProdutoResponseDTO> conversor(Page<Produto> produtos){
        return produtos.map(ProdutoResponseDTO::new);
    }

}
