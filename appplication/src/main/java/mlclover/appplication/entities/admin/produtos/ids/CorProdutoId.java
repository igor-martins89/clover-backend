package mlclover.appplication.entities.admin.produtos.ids;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CorProdutoId implements Serializable {

    @Column(name = "cor_id")
    private String corId;

    @Column(name = "produto_id")
    private Integer produtoId;
}
