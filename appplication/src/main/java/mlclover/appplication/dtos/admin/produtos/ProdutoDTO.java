package mlclover.appplication.dtos.admin.produtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mlclover.appplication.dtos.admin.classificacoes.SubcategoriaDTO;
import mlclover.appplication.entities.admin.produtos.Produto;
import org.springframework.data.domain.Page;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {

    private Integer id;
    private String nome;
    private String descricao;
    private Double valor;
    private Set<String> tamanhos = new HashSet<>();
    private List<SubcategoriaDTO> subcategorias;

}
