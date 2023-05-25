package mlclover.appplication.controllers.admin.classificacoes;

import mlclover.appplication.dtos.admin.classificacoes.CategoriaDTO;
import mlclover.appplication.services.admin.classificacoes.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categorias")
@CrossOrigin("*")
public class CategoriaController {

    @Autowired
    CategoriaService service;


    @PostMapping
    public ResponseEntity<List<CategoriaDTO>> cadastrarCategoria(@Valid @RequestBody CategoriaDTO dto){
        List<CategoriaDTO> lista = service.cadastrarCategoria(dto);
        return ResponseEntity.status(201).body(lista);
    }

    /**
     Não existe um getCategorias. Ela é obtida no /colecoes, onde as categorias são listas de coleções.
     */

}
