package mlclover.appplication.entities.admin.classificacoes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mlclover.appplication.entities.admin.classificacoes.ids.ColecaoCategoriaId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_colecao_categoria")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ColecaoCategoria implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    ColecaoCategoriaId id;

    @ManyToOne
    @JoinColumn(name = "colecao_id", insertable = false, updatable = false)
    private Colecao colecaoId;

    @ManyToOne
    @JoinColumn(name = "categoria_id", insertable = false, updatable = false)
    private Categoria categoriaId;

    @JsonIgnore
    @OneToMany(mappedBy = "colecaoCategoriaId")
    private List<CategoriaSubcategoria> produtos = new ArrayList<>();

    public ColecaoCategoria(ColecaoCategoriaId id, Colecao colecaoId, Categoria categoriaId) {
        this.id = id;
        this.colecaoId = colecaoId;
        this.categoriaId = categoriaId;
    }
}
