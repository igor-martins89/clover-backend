package mlclover.appplication.controllers.admin.classificacoes;

import mlclover.appplication.dtos.admin.classificacoes.ColecaoDTO;
import mlclover.appplication.dtos.admin.classificacoes.ColecaoResponseDTO;
import mlclover.appplication.entities.admin.classificacoes.Colecao;
import mlclover.appplication.services.admin.classificacoes.ColecaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/colecoes")
@CrossOrigin("*")
public class ColecaoController {

    @Autowired
    ColecaoService service;

    @PostMapping
    public ResponseEntity<List<ColecaoDTO>> cadastroColecao(@Valid @RequestBody ColecaoDTO dto){
        List<ColecaoDTO> lista = service.cadastroColecao(dto);
        return ResponseEntity.status(201).body(lista);
    }

    /**
     Retorna todas as coleções. Cada coleção contém uma lista com todas as suas respectivas categorias.
     Cada categoria, por sua vez, contém uma lista com todas as suas respectivas subcategorias.
     */

    @GetMapping
    public ResponseEntity<List<ColecaoResponseDTO>> listaColecoes(){
        List<ColecaoResponseDTO> lista =  service.listaColecoes();

        return ResponseEntity.status(200).body(lista);
    }

}
