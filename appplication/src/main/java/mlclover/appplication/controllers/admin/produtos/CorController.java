package mlclover.appplication.controllers.admin.produtos;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mlclover.appplication.dtos.admin.produtos.CorDTO;
import mlclover.appplication.services.admin.produtos.CorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/cores")
@CrossOrigin("*")
@Api("Address REST API")
public class CorController {

    @Autowired
    CorService service;

    @PostMapping
    @ApiOperation(value = "Cadastra uma cor")
    public ResponseEntity<List<CorDTO>> cadastrarCor(@Valid @RequestBody CorDTO dto){
        List<CorDTO> lista = service.cadastrarCor(dto);
        return ResponseEntity.status(201).body(lista);
    }

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de cores")
    public ResponseEntity<List<CorDTO>> listaCores(){
        List<CorDTO> lista = service.listaCores();

        return lista.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(lista);
    }
}
