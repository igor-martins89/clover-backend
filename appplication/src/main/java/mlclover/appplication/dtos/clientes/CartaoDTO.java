package mlclover.appplication.dtos.clientes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mlclover.appplication.entities.enums.Tipo;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartaoDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Size(max= 60, message = "O tamanho deve de {max} caracteres.")
    private String titular;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Size(max= 16, message = "O tamanho deve de {max} caracteres.")
    private String numero;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String ultimosQuatroDigitos;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/yy", locale = "pt-BR", timezone = "America/Sao_Paulo")
    private Date dataVencimento;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Size(max= 4, message = "O tamanho deve de até {max} caracteres.")
    private String cvv;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer tipoCartao;
}
