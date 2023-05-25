package mlclover.appplication.entities.clientes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mlclover.appplication.entities.enums.Tipo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_cartao")
public class Cartao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titular;
    private String numero;
    private String ultimosQuatroDigitos;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/yy", locale = "pt-BR", timezone = "America/Sao_Paulo")
    private Date dataVencimento;
    private String cvv;
    private Integer tipoCartao;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    /** Construtor que passa como argumento o tipo do objeto (DEFAULT ou ADITIONAL) */

    public Cartao(Integer id, String titular, String numero, String ultimosQuatroDigitos, Date dataVencimento, String cvv, Tipo tipoCartao, Cliente cliente) {
        this.id = id;
        this.titular = titular;
        this.numero = numero;
        this.ultimosQuatroDigitos = ultimosQuatroDigitos;
        this.dataVencimento = dataVencimento;
        this.cvv = cvv;
        this.tipoCartao = tipoCartao.getCod();
        this.cliente = cliente;
    }
}
