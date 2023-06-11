package mlclover.appplication.controllers.clientes;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mlclover.appplication.dtos.clientes.ClienteCadastroAdicionalDTO;
import mlclover.appplication.dtos.clientes.ClienteCadastroInicialDTO;
import mlclover.appplication.dtos.clientes.ClienteDTO;
import mlclover.appplication.dtos.clientes.ClienteLoginDTO;
import mlclover.appplication.services.clientes.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value="/v1/clientes")
@CrossOrigin("*")
@Api("Address REST API")
public class ClienteController {

    @Autowired
    ClienteService service;

    /** Cadastro inicial de cliente, apenas com os atributos iniciais */

    @PostMapping(value = "/cadastro")
    @ApiOperation(value = "Cadastra um cliente com suas informações iniciais")
    public ResponseEntity<Void> cadastroInicial( @Valid @RequestBody ClienteCadastroInicialDTO objDTO){

        objDTO = service.cadastroInicial(objDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(objDTO.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    /** Cadastro das informações adicionais do cliente, como CPF */

    @PutMapping(value = "/cadastro/{id}")
    @ApiOperation(value = "Atualiza as informações de cliente, acrescentando informações adicionais")
    public ResponseEntity<Void> cadastroAdicional(@Valid @RequestBody ClienteCadastroAdicionalDTO dto, @PathVariable int id){

        service.cadastroAdicional(dto, id);

        return ResponseEntity.status(201).build();
    }

    /** Busca todas as informações do cliente de um determinado ID */

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Busca cliente por id")
    public ResponseEntity<ClienteDTO> buscaPorId(@PathVariable int id){

        ClienteDTO obj = service.buscaPorId(id);

        return ResponseEntity.status(200).body(obj);
    }

    /** Log-in de cliente, substituindo o JWT  */

    @PostMapping(value = "/login")
    @ApiOperation(value = "Realiza o login do cliente")
    public ResponseEntity<ClienteDTO> login(@Valid @RequestBody ClienteLoginDTO objDTO){

        ClienteDTO obj = service.login(objDTO);

        return ResponseEntity.status(200).body(obj);
    }

    /** Log-off de cliente, substituindo o JWT  */

    @PostMapping(value = "logoff/{id}")
    @ApiOperation(value = "Realiza o logoff do cliente")
    public ResponseEntity<Void> logoff(@PathVariable int id){

        service.logoff(id);

        return ResponseEntity.status(204).build();
    }

}
