package mlclover.appplication.controllers.admin.classificacoes;

import mlclover.appplication.dtos.admin.classificacoes.CategoriaDTO;
import mlclover.appplication.dtos.admin.classificacoes.SubcategoriaDTO;
import mlclover.appplication.services.admin.classificacoes.SubcategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/subcategorias")
@CrossOrigin("*")
public class SubcategoriaController {

    @Autowired
    SubcategoriaService service;

    @PostMapping
    public ResponseEntity<List<SubcategoriaDTO>> cadastrarSubcategoria(@Valid @RequestBody SubcategoriaDTO dto){
        List<SubcategoriaDTO> lista = service.cadastrarSubcategoria(dto);
        return ResponseEntity.status(201).body(lista);
    }


    /**
     Não existe um getSubcategorias. Ela é obtida no /colecoes, onde as subcategorias são listas de
     categorias, que, por sua vez, são listas de coleções
     */
}
