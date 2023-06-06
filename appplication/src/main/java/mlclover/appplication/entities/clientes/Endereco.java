package mlclover.appplication.entities.clientes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mlclover.appplication.entities.clientes.enums.Tipo;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_endereco")
public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable=false, length=8)
    private String cep;
    @Column(nullable=false, length=45)
    private String rua;
    @Column(nullable=false, length=10)
    private String numero;
    @Column(nullable=false, length=20)
    private String complemento;
    @Column(nullable=false, length=50)
    private String bairro;
    @Column(nullable=false, length=45)
    private String cidade;
    @Column(nullable=false, length=30)
    private String estado;
    @Column(nullable=false, length=60)
    private String destinatario;

    private Integer tipoEndereco;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    /** Construtor que passa como argumento o tipo do objeto (DEFAULT ou ADITIONAL) */

    public Endereco(Integer id, String cep, String rua, String numero, String complemento, String bairro, String cidade, String estado, String destinatario, Tipo tipoEndereco, Cliente cliente) {
        this.id = id;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.destinatario = destinatario;
        this.tipoEndereco = tipoEndereco.getCod();
        this.cliente = cliente;
    }
}
