package mlclover.appplication.dtos.clientes;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mlclover.appplication.services.validation.IdentificadorCliente;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdentificadorCliente
public class ClienteCadastroAdicionalDTO {

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "America/Sao_Paulo")
    private Date dataNascimento;

    private String cpfOuCnpj;
    /**
     Os Tipos referem-se a PF ou PJ

     PESSOA_FISICA = tipo 1
     PESSOA_JURIDICA = tipo 2
     */
    private Integer tipo;

}
