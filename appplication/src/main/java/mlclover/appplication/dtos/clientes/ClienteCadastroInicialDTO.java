package mlclover.appplication.dtos.clientes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCadastroInicialDTO {


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Size(min = 5, max= 120, message = "O tamanho deve ser entre {min} e {max} caracteres.")
    private String nome;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "E-mail inválido")
    private String email;
    @NotEmpty(message = "Preenchimento obrigatório")
    private String telefone;
    @NotEmpty(message = "Preenchimento obrigatório")

    private String senha;
    @NotEmpty(message = "Preenchimento obrigatório")
    private String genero;
}
