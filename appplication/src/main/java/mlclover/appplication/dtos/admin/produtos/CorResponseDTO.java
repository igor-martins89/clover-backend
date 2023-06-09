package mlclover.appplication.dtos.admin.produtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CorResponseDTO {
    private String hexadecimal;
    private String nome;
    private Set<String> imagens = new HashSet<>();
}
