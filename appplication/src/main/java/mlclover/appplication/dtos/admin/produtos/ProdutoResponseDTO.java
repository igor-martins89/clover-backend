package mlclover.appplication.dtos.admin.produtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mlclover.appplication.entities.admin.produtos.CorProduto;
import mlclover.appplication.entities.admin.produtos.Produto;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    private List<CorResponseDTO> cores = new ArrayList<>();

    public ProdutoResponseDTO(Produto produto) {
        this.id = produto.getId();;
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.descricao = produto.getDescricao();
        this.tamanhos = produto.getTamanhos();

        this.cores = produto.getCorProdutoId().stream().map( corProduto ->
            new CorResponseDTO(
                    corProduto.getCorId().getHexadecimal(),
                    corProduto.getCorId().getNome(),
                    corProduto.getImagens())
        ).collect(Collectors.toList());

    }

    public static Page<ProdutoResponseDTO> conversor(Page<Produto> produtos){
        return produtos.map(ProdutoResponseDTO::new);
    }

    public static List<ProdutoResponseDTO> conversor(List<Produto> produtos) {
        return produtos.stream()
                .map(ProdutoResponseDTO::new)
                .collect(Collectors.toList());
    }
}
