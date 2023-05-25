package mlclover.appplication.dtos.clientes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mlclover.appplication.entities.enums.Tipo;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EnderecoDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Size(max= 8, message = "O tamanho deve de {max} caracteres.")
    private String cep;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Size(max= 45, message = "O tamanho deve de até {max} caracteres.")
    private String rua;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Size(max= 10, message = "O tamanho deve de até {max} caracteres.")
    private String numero;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Size(max= 20, message = "O tamanho deve de até {max} caracteres.")
    private String complemento;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Size(max= 50, message = "O tamanho deve de até {max} caracteres.")
    private String bairro;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Size(max= 45, message = "O tamanho deve de até {max} caracteres.")
    private String cidade;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Size(max= 30, message = "O tamanho deve de até {max} caracteres.")
    private String estado;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Size(max= 60, message = "O tamanho deve de até {max} caracteres.")
    private String destinatario;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Tipo tipoEndereco;
}
