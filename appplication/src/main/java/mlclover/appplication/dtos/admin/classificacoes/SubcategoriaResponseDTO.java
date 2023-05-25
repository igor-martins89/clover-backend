package mlclover.appplication.dtos.admin.classificacoes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubcategoriaResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
}
