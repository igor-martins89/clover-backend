package mlclover.appplication.entities.admin.produtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_cor")
public class Cor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable=false, length=10)
    private String hexadecimal;
    @Column(nullable=false, length=60)
    private String nome;

    @OneToMany(mappedBy = "corId")
    private List<CorProduto> corProdutoId = new ArrayList<>();

}
