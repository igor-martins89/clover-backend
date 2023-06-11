package mlclover.appplication.controllers.admin.classificacoes;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/v1/colecoes")
@CrossOrigin("*")
@Api("Address REST API")
public class ColecaoController {

    @Autowired
    ColecaoService service;

    @PostMapping
    @ApiOperation(value = "Cadastra uma coleção")
    public ResponseEntity<List<ColecaoDTO>> cadastroColecao(@Valid @RequestBody ColecaoDTO dto){
        List<ColecaoDTO> lista = service.cadastroColecao(dto);
        return ResponseEntity.status(201).body(lista);
    }

    /**
     Retorna todas as coleções. Cada coleção contém uma lista com todas as suas respectivas categorias.
     Cada categoria, por sua vez, contém uma lista com todas as suas respectivas subcategorias.
     */

    @GetMapping
    @ApiOperation(value = "Retorna a lista de coleções, em conjunto com a lista de categorias e a lista de subcategorias")
    public ResponseEntity<List<ColecaoResponseDTO>> listaColecoes(){
        List<ColecaoResponseDTO> lista =  service.listaColecoes();

        return ResponseEntity.status(200).body(lista);
    }

}
