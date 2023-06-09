package mlclover.appplication.dtos.clientes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mlclover.appplication.entities.clientes.Cliente;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

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
    private String genero;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "America/Sao_Paulo")
    private Date dataNascimento;

    private String cpfOuCnpj;

    private List<Integer> perfis = new ArrayList<>();
    private boolean isLogado;


    public ClienteDTO(Cliente obj){
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.email = obj.getEmail();

        // Se o perfil == 1 => ADMIN
        // Se o perfil == 2 => CLIENTE
        this.perfis = obj.getPerfis().stream().map(x -> x.getCod()).collect(Collectors.toList());
        this.isLogado = obj.isLogado();
    }


}
