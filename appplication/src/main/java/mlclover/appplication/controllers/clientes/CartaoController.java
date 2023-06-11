package mlclover.appplication.controllers.clientes;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mlclover.appplication.dtos.clientes.CartaoDTO;
import mlclover.appplication.services.clientes.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/v1/cartoes")
@CrossOrigin("*")
@Api("Address REST API")
public class CartaoController {

    @Autowired
    CartaoService service;

    /** Cadastro de Cartao vinculado a um Id de Cliente. Retorna a lista de Cartoes, sendo o último cartao cadastrado
     o DEFAULT, e o resto como ADITIONAL
     */

    @PostMapping(value = "/{idCliente}")
    @ApiOperation(value = "Cadastra um cartão")
    public ResponseEntity<List<CartaoDTO>> cadastroCartao(@Valid @RequestBody CartaoDTO dto, @PathVariable int idCliente){

        List<CartaoDTO> lista = service.cadastroCartao(dto, idCliente);

        return ResponseEntity.status(201).body(lista);
    }

    /** Atualização de Cartao passando o id do Cliente e o id do endereço a se atualizar. Essa atualização é relacionada
     as credenciais do Cartao, como numero, CVV e outros atributos. NÃO tem relação com escolher o cartao DEFAULT
     */

    @PutMapping(value = "/{idCliente}/{idCartao}")
    @ApiOperation(value = "Atualiza um cartão")
    public ResponseEntity<List<CartaoDTO>> atualizacaoCartao(@Valid @RequestBody CartaoDTO dto, @PathVariable int idCliente, @PathVariable int idCartao){

        List<CartaoDTO> lista = service.atualizacaoCartao(dto, idCliente, idCartao);

        return ResponseEntity.status(200).body(lista);
    }

    /** Único objetivo do endpoint é atualizar o cartao padrão, colocando os demais cartoes como ADITIONAL  */

    @PutMapping(value = "/{idCliente}/cartao-padrao/{idCartao}")
    @ApiOperation(value = "Atualiza o cartão principal do cliente")
    public ResponseEntity<List<CartaoDTO>> atualizacaoCartaoPadrao(@PathVariable int idCliente, @PathVariable int idCartao){

        List<CartaoDTO> lista = service.atualizacaoCartaoPadrao(idCliente, idCartao);

        return ResponseEntity.status(200).body(lista);
    }

    /** Deletar cartao. Caso o cartao a ser deletado seja DEFAULT e ainda existirem outros cartoes associados ao ID
     do cliente, o próximo ID de Cartao se tornará DEFAULT. Caso não existam mais cartoes associados, retorna um NO_CONTENT
     */

    @DeleteMapping(value = "{idCliente}/{idCartao}")
    @ApiOperation(value = "Deleta um cartão")
    public ResponseEntity<List<CartaoDTO>> deletarCartao(@PathVariable int idCliente, @PathVariable int idCartao){

        List<CartaoDTO> lista = service.deletarCartao(idCliente, idCartao);

        return !lista.isEmpty() ? ResponseEntity.status(200).body(lista) : ResponseEntity.status(204).build();
    }

    /** Busca todos os cartoes associados a um ID de Cliente */

    @GetMapping(value = "{idCliente}")
    @ApiOperation(value = "Retorna todos os cartões de determinado cliente")
    public ResponseEntity<List<CartaoDTO>> buscaListaCartoesPorClienteId(@PathVariable int idCliente){

        List<CartaoDTO> lista = service.buscaListaCartoesPorClienteId(idCliente);

        return !lista.isEmpty() ? ResponseEntity.status(200).body(lista) : ResponseEntity.status(204).build();

    }

}
