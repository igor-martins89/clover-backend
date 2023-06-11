package mlclover.appplication.controllers.admin.produtos;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mlclover.appplication.dtos.admin.produtos.ProdutoCadastroDTO;
import mlclover.appplication.dtos.admin.produtos.ProdutoResponseDTO;
import mlclover.appplication.services.admin.produtos.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/v1/produtos")
@CrossOrigin("*")
@Api("Address REST API")
public class ProdutoController {

    @Autowired
    ProdutoService service;


    @PostMapping
    @ApiOperation(value = "Cadastra um produto")
    public ResponseEntity<List<ProdutoCadastroDTO>> cadastrarProduto(@Valid @RequestBody ProdutoCadastroDTO dto){
        List<ProdutoCadastroDTO> lista = service.cadastrarProduto(dto);
        return ResponseEntity.status(201).body(lista);
    }

    /**

     Endpoint responsável por retornar uma consulta de produtos, com paginação.
     Existem algumas formas de se realizar essa consulta:

     1- Não passar nenhum parâmetro: Retornará todos os produtos disponíveis;
     2- Passar o id de coleção: Retornará todos os produtos da coleção;
     3- Passar o id de coleção e o id de categoria: Retornará todos os produtos da categoria associada a determinada coleção;
     4- Passar o id de coleção, id de categoria e id de subcategoria. Retornará todos os produtos da subcategoria associada
     a determinada categoria associada a determinada coleção;
     5- Passar o nome do produto (WHERE nome_produto = '%nomeEnviado%'). Retornará todos os produtos que contenham
     a String enviada no nome.

     Além disso é possível enviar a página respectiva, a quantidade de produtos por página, o tipo de ordenação e a direção.
     Nenhum desses campos é obrigatório.

     Possíveis erros:

     1- Enviar idCategoria sem o idColecao
     2- Enviar o idSubcategoria sem o idCategoria e o idColecao
     3- Enviar algum id (idColecao, idCategoria, idSubcategoria) inexistente

     Em qualquer um desses cenários retornará uma exceção.

     */

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de produtos de acordo com os parâmetros* passados")
    public ResponseEntity<Page<ProdutoResponseDTO>> listaProdutos(
            @RequestParam(value="colecao", defaultValue="0") int idColecao,
            @RequestParam(value="categoria", defaultValue="0") int idCategoria,
            @RequestParam(value="subcategoria", defaultValue="0") int idSubcategoria,
            @RequestParam(value = "nome", defaultValue = "") String nomeProduto,
            @RequestParam(value="pagina", defaultValue="0") int pagina,
            @RequestParam(value="linhasPorPagina", defaultValue="8") int linhasPorPagina,
            @RequestParam(value="orderBy", defaultValue="nome") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direcao
    ){


        Page<ProdutoResponseDTO> produtos = service.listaProdutosPaginados(idColecao, idCategoria, idSubcategoria, nomeProduto, pagina, linhasPorPagina, orderBy, direcao);
        return ResponseEntity.status(200).body(produtos);
    }

    /**
        Os tamanhos dos produtos (PP, P, M, G, GG...) são obtidos através da própria entidade Produto
        Para isso basta acessar o endpoint:
     * */

    @GetMapping("/tamanhos")
    @ApiOperation(value = "Retorna uma lista dos tamanhos dos produtos")
    public ResponseEntity<Set<String>> listaTamanhos(){

        Set<String> tamanhos = service.listaTamanhos();

        return tamanhos.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(tamanhos);
    }

    @PostMapping("/imagens")
    @ApiOperation(value = "Cadastra as imagens correspondentes aos produtos")
    public ResponseEntity<String> cadastroImagem(
            @RequestParam(value = "files") MultipartFile[] files,
            @RequestParam(value = "idProduto") Integer idProduto,
            @RequestParam(value = "hexadecimal") String hexadecimal
    ){
        boolean cadastradoComSucesso = service.cadastroImagem(files, idProduto, hexadecimal);

        String mensagem = cadastradoComSucesso ? "Imagens cadastradas com sucesso" : "Erro ao cadastrar as imagens";
        HttpStatus status = cadastradoComSucesso ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(status).body(mensagem);
    }

    @GetMapping("/novidades")
    @ApiOperation(value = "Retorna os 12 últimos produtos cadastrados")
    public ResponseEntity<List<ProdutoResponseDTO>> novosProdutos(){
        List<ProdutoResponseDTO> lista = service.novosProdutos();

        return lista.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(lista);
    }


}
