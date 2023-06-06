package mlclover.appplication.dtos.admin.produtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mlclover.appplication.entities.admin.produtos.Produto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {

    private Integer id;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Size(min = 2, max= 80, message = "O tamanho deve ser entre {min} e {max} caracteres.")
    private String nome;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double preco;

    public ProdutoDTO(Produto obj){
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.preco = obj.getValor();
    }
}
