package mlclover.appplication.controllers.clientes;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mlclover.appplication.dtos.clientes.EnderecoDTO;
import mlclover.appplication.services.clientes.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/v1/enderecos")
@CrossOrigin("*")
@Api("Address REST API")
public class EnderecoController {

    @Autowired
    EnderecoService service;

    /** Cadastro de Endereco vinculado a um Id de Cliente. Retorna a lista de Enderecos, sendo o último endereço cadastrado
     o DEFAULT, e o resto como ADITIONAL
      */

    @PostMapping(value = "/{idCliente}")
    @ApiOperation(value = "Cadastra endereço")
    public ResponseEntity<List<EnderecoDTO>> cadastroEndereco(@Valid @RequestBody EnderecoDTO dto, @PathVariable int idCliente){

        List<EnderecoDTO> lista = service.cadastroEndereco(dto, idCliente);

        return ResponseEntity.status(201).body(lista);
    }

    /** Atualização de Endereço passando o id do Cliente e o id do endereço a se atualizar. Essa atualização é relacionada
     as credenciais do Endereço, como CEP, bairro e outros atributos. NÃO tem relação com escolher o endereço DEFAULT
      */

    @PutMapping(value = "/{idCliente}/{idEndereco}")
    @ApiOperation(value = "Atualiza informações de endereço")
    public ResponseEntity<List<EnderecoDTO>> atualizacaoEndereco(@Valid @RequestBody EnderecoDTO dto, @PathVariable int idCliente, @PathVariable int idEndereco){

        List<EnderecoDTO> lista = service.atualizacaoEndereco(dto, idCliente, idEndereco);

        return ResponseEntity.status(200).body(lista);
    }

    /** Único objetivo do endpoint é atualizar o endereço padrão, colocando os demais endereços como ADITIONAL  */

    @PutMapping(value = "/{idCliente}/endereco-padrao/{idEndereco}")
    @ApiOperation(value = "Atualiza endereço padrão de cliente")
    public ResponseEntity<List<EnderecoDTO>> atualizacaoEnderecoPadrao(@PathVariable int idCliente, @PathVariable int idEndereco){

        List<EnderecoDTO> lista = service.atualizacaoEnderecoPadrao(idCliente, idEndereco);

        return ResponseEntity.status(200).body(lista);
    }

    /** Deletar endereço. Caso o endereço a ser deletado seja DEFAULT e ainda existirem outros endereços associados ao ID
     do cliente, o próximo ID de Endereço se tornará DEFAULT. Caso não existam mais endereços associados, retorna um NO_CONTENT
      */

    @DeleteMapping(value = "{idCliente}/{idEndereco}")
    @ApiOperation(value = "Deleta endereço")
    public ResponseEntity<List<EnderecoDTO>> deletarEndereco(@PathVariable int idCliente, @PathVariable int idEndereco){

        List<EnderecoDTO> lista = service.deletarEndereco(idCliente, idEndereco);

        return !lista.isEmpty() ? ResponseEntity.status(200).body(lista) : ResponseEntity.status(204).build();
    }

    /** Busca todos os endereços associados a um ID de Cliente */

    @GetMapping(value = "{idCliente}")
    @ApiOperation(value = "Busca todos os endereços de determinado cliente")
    public ResponseEntity<List<EnderecoDTO>> buscaListaEnderecosPorClienteId(@PathVariable int idCliente){

        List<EnderecoDTO> lista = service.buscaListaEnderecosPorClienteId(idCliente);

        return !lista.isEmpty() ? ResponseEntity.status(200).body(lista) : ResponseEntity.status(204).build();

    }

}
