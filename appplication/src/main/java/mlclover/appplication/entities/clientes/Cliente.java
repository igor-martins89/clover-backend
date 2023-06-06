package mlclover.appplication.entities.clientes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mlclover.appplication.entities.clientes.enums.Perfil;
import mlclover.appplication.entities.clientes.enums.TipoCliente;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_cliente")
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable=false, length=60)
    private String nome;
    @Column(nullable=false, length=100)
    private String email;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "America/Sao_Paulo")
    private Date dataNascimento;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable=false, length=25)
    private String senha;
    @Column(nullable=false, length=15)
    private String telefone;
    @Column(length=14)
    private String cpfOuCnpj;
    @Column(length=1)
    private Integer tipo;
    @Column(nullable=false, length=1)
    private String genero;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "TB_PERFIL")
    private Set<Integer> perfis = new HashSet<>();

    /** Atributo para validação de login, substituindo o JWT */
    private boolean isLogado;

    /** Relacionamentos */

    @OneToMany(mappedBy = "cliente")
    private List<Endereco> enderecos = new ArrayList<>();
    @OneToMany(mappedBy = "cliente")
    private List<Cartao> cartoes = new ArrayList<>();

    /** Construtor com enumerador TipoCLiente */

    public Cliente(Integer id, String nome, String email, String senha, String telefone, String cpfOuCnpj, TipoCliente tipo, String genero, Date dataNascimento, List<Endereco> enderecos, List<Cartao> cartoes, boolean isLogado) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipo = (tipo == null) ? null : tipo.getCod();
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.enderecos = enderecos;
        this.cartoes = cartoes;
        this.isLogado = isLogado;
    }

    public void addPerfil(Perfil perfil){
        perfis.add(perfil.getCod());
    }

    public Set<Perfil> getPerfis(){
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

}
