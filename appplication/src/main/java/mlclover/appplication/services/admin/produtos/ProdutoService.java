package mlclover.appplication.services.admin.produtos;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import mlclover.appplication.dtos.admin.classificacoes.CategoriaDTO;
import mlclover.appplication.dtos.admin.classificacoes.ColecaoDTO;
import mlclover.appplication.dtos.admin.classificacoes.SubcategoriaDTO;
import mlclover.appplication.dtos.admin.produtos.ProdutoDTO;
import mlclover.appplication.dtos.admin.produtos.ProdutoResponseDTO;
import mlclover.appplication.entities.admin.classificacoes.*;
import mlclover.appplication.entities.admin.classificacoes.ids.CategoriaSubcategoriaId;
import mlclover.appplication.entities.admin.classificacoes.ids.ColecaoCategoriaId;
import mlclover.appplication.entities.admin.classificacoes.ids.ProdutoSubcategoriaId;
import mlclover.appplication.entities.admin.produtos.Produto;
import mlclover.appplication.repositories.admin.produtos.ProdutoRepository;
import mlclover.appplication.services.admin.classificacoes.CategoriaService;
import mlclover.appplication.services.admin.classificacoes.ColecaoService;
import mlclover.appplication.services.admin.classificacoes.ProdutoSubcategoriaService;
import mlclover.appplication.services.admin.classificacoes.SubcategoriaService;
import mlclover.appplication.services.exceptions.BadCredentialsException;
import mlclover.appplication.services.exceptions.CategoriaSemAssociacaoException;
import mlclover.appplication.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository repository;

    @Autowired
    SubcategoriaService subcategoriaService;

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    ColecaoService colecaoService;

    @Autowired
    ProdutoSubcategoriaService produtoSubcategoriaService;

    @Autowired
    CorProdutoService corProdutoService;

    @Autowired
    private AmazonS3 s3Client;

    @Value("${application.bucket.name}")
    private String bucketName;


    public List<Produto> buscaListaProdutosPorId(List<Integer> ids) {

        List<Produto> lista = new ArrayList<>();

        for(Integer i : ids){
            Produto produto = repository.findById(i).orElseThrow(()
                    -> new EntityNotFoundException("Id " + i + " de produto não encontrado"));
            lista.add(produto);
        }
        return lista;
    }

    public List<ProdutoDTO> cadastrarProduto(ProdutoDTO dto) {
        subcategoriaService.hasListaSubcategorias(dto.getSubcategorias());


        Produto produto = converterProdutoDTOEmProduto(dto);

        /**
         Salva o Produto no banco de dados para gerar um ID
         */
        produto = repository.save(produto);

        /**
        Cria as associações entre o Produto com Subcategoria, Produto com Categorias e Produto com Colecao  usando o ID gerado
        */

        List<ProdutoSubcategoria> lista = organizarRelacionamentosDeProduto(produto, dto);


        lista = produtoSubcategoriaService.cadastrarListaDeRelacionamentos(lista);


        produto.setProdutoSubcategoriaId(lista);


        List<Produto> listaProdutos = repository.findAll();

         /**
        Adiciona as coleções, as categorias e as subcategorias associadas ao novo produto à lista de produtos
         */


        return converterListaProdutoEmListaProdutoDTO(listaProdutos);


    }

    private List<ProdutoDTO> converterListaProdutoEmListaProdutoDTO(List<Produto> produtos) {
        return produtos.stream().map(produto -> converterProdutoEmProdutoDTO(produto)).collect(Collectors.toList());
    }

    private List<ProdutoSubcategoria> organizarRelacionamentosDeProduto(Produto produto, ProdutoDTO dto) {

        if(dto.getSubcategorias().isEmpty())
            throw new CategoriaSemAssociacaoException("P produto não tem associação com nenhuma subcategoria");

        List<ProdutoSubcategoria> listaProdutoSubcategoria = new ArrayList<>();

        for(SubcategoriaDTO subcategoriaDTO : dto.getSubcategorias()){
            Subcategoria subcategoria = subcategoriaService.converterSubcategoriaDTOEmSubcategoria(subcategoriaDTO);
            for(CategoriaDTO categoriaDTO : subcategoriaDTO.getCategorias()){
                Categoria categoria = categoriaService.converterCategoriaDTOEmCategoria(categoriaDTO);
                for(ColecaoDTO colecaoDTO : categoriaDTO.getColecoes()){
                    Colecao colecao = colecaoService.converterColecaoDTOEmColecao(colecaoDTO);

                    ProdutoSubcategoria produtoSubcategoria =
                            new ProdutoSubcategoria(
                                new ProdutoSubcategoriaId(
                                        colecao.getId(),
                                        categoria.getId(),
                                        subcategoria.getId(),
                                        produto.getId()
                                ),
                                new CategoriaSubcategoria(
                                        new CategoriaSubcategoriaId(
                                                colecao.getId(),
                                                categoria.getId(),
                                                subcategoria.getId()
                                        ),
                                        new ColecaoCategoria(
                                                new ColecaoCategoriaId(
                                                        colecao.getId(),
                                                        categoria.getId()
                                                ),
                                                colecao,
                                                categoria),
                                        subcategoria),
                                    produto
                                );

                    listaProdutoSubcategoria.add(produtoSubcategoria);

                }
            }

        }

        return listaProdutoSubcategoria;
    }

    private Produto converterProdutoDTOEmProduto(ProdutoDTO dto) {

        Produto obj = new Produto();
        obj.setNome(dto.getNome());
        obj.setDescricao(dto.getDescricao());
        obj.setValor(dto.getValor());
        obj.setTamanhos(dto.getTamanhos());

        return obj;
    }

    private ProdutoDTO converterProdutoEmProdutoDTO(Produto obj){
        ProdutoDTO dto = new ProdutoDTO();

        dto.setId(obj.getId());
        dto.setNome(obj.getNome());
        dto.setDescricao(obj.getNome());
        dto.setValor(obj.getValor());
        dto.setTamanhos(obj.getTamanhos());
        dto.setSubcategorias(
                obj.getProdutoSubcategoriaId()
                        .stream().
                        map(
                                subcategoria ->
                                        subcategoriaService.converterSubcategoriaEmSubcategoriaDTO(
                                                subcategoria
                                                        .getCategoriaSubcategoriaId()
                                                        .getSubcategoriaId()
                                        )
                        )
                        .collect(Collectors.toList()));

        return dto;
    }


    public Page<ProdutoResponseDTO> listaProdutosPaginados(int idColecao, int idCategoria, int idSubcategoria, String nomeProduto, int pagina, int linhasPorPagina, String orderBy, String direcao) {
        Pageable paginacao = PageRequest.of(pagina, linhasPorPagina, Sort.Direction.valueOf(direcao), orderBy);

        validarRequisicoesParaBuscaDeProdutos(idColecao, idCategoria, idSubcategoria);

        if (nomeProduto.length() > 0) {
            return buscarProdutosPorNome(nomeProduto, paginacao);
        } else if (idSubcategoria == 0 && idCategoria == 0 && idColecao == 0) {
            return buscarTodosProdutos(paginacao);
        } else if (idSubcategoria == 0 && idCategoria == 0 && idColecao > 0) {
            return buscarProdutosPorColecao(idColecao, paginacao);
        } else if (idSubcategoria == 0 && idCategoria > 0 && idColecao > 0) {
            return buscarProdutosPorColecaoECategoria(idColecao, idCategoria, paginacao);
        } else if(idSubcategoria > 0 && idCategoria > 0 && idColecao > 0) {
            return buscarProdutosPorColecaoCategoriaESubcategoria(idColecao, idCategoria, idSubcategoria, paginacao);
        } else{
            throw new BadCredentialsException("Não foi possível encontrar os produtos. Informações passadas inválidas");
        }
    }

    private Page<ProdutoResponseDTO> buscarProdutosPorNome(String nomeProduto, Pageable paginacao) {
        Page<Produto> produtos = repository.findDistinctByNomeContainingIgnoreCase(nomeProduto, paginacao);
        return ProdutoResponseDTO.conversor(produtos);
    }

    private Page<ProdutoResponseDTO> buscarTodosProdutos(Pageable paginacao) {
        Page<Produto> produtos = repository.findAll(paginacao);
        return ProdutoResponseDTO.conversor(produtos);
    }

    private Page<ProdutoResponseDTO> buscarProdutosPorColecao(int idColecao, Pageable paginacao) {
        Page<Produto> produtos = repository.findByColecaoId(idColecao, paginacao);
        return ProdutoResponseDTO.conversor(produtos);
    }

    private Page<ProdutoResponseDTO> buscarProdutosPorColecaoECategoria(int idColecao, int idCategoria, Pageable paginacao) {
        Page<Produto> produtos = repository.findByColecaoIdAndCategoriaId(idColecao, idCategoria, paginacao);
        return ProdutoResponseDTO.conversor(produtos);
    }

    private Page<ProdutoResponseDTO> buscarProdutosPorColecaoCategoriaESubcategoria(int idColecao, int idCategoria, int idSubcategoria, Pageable paginacao) {
        Page<Produto> produtos = repository.findByColecaoIdAndCategoriaIdAndSubcategoriaId(idColecao, idCategoria, idSubcategoria, paginacao);
        return ProdutoResponseDTO.conversor(produtos);
    }


    private boolean validarRequisicoesParaBuscaDeProdutos(int idColecao, int idCategoria, int idSubcategoria){
        if(idColecao > 0){
            Colecao colecao = colecaoService.colecaoExists(idColecao);
            if(colecao == null){
                throw new EntityNotFoundException("Colecao " + idColecao + " não encontrada");
            }
        }
        if (idCategoria > 0) {
            Categoria categoria = categoriaService.categoriaExists(idCategoria);
            if(categoria == null){
                throw new EntityNotFoundException("Categoria " + idCategoria + " não encontrada");
            }
        }
        if(idSubcategoria > 0){
            Subcategoria subcategoria = subcategoriaService.subcategoriaExists(idSubcategoria);
            if(subcategoria == null){
                throw new EntityNotFoundException("Subcategoria " + idSubcategoria + " não encontrada");
            }
        }
    return true;
    }


    public Set<String> listaTamanhos() {
        return repository.getAllTamanhos();
    }

    public boolean cadastroImagem(MultipartFile[] files, Integer idProduto, String hexadecimal) {
        boolean cadastradoComSucesso = true;
        for(MultipartFile file : files){
            try {
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                String contentType = file.getContentType();
                InputStream is = file.getInputStream();

                URI url = uploadArquivoparaS3(is, fileName, contentType);

                Produto produto = repository.findById(idProduto).orElseThrow(()
                        -> new EntityNotFoundException("Id " + idProduto + " de produto não encontrado"));

                corProdutoService.cadastroImagem(produto, hexadecimal, url.toString());
            } catch (IOException e) {
                cadastradoComSucesso = false;
            }
        }
        return cadastradoComSucesso;
    }

    public URI uploadArquivoparaS3(InputStream is, String fileName, String contentType){
        try {
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType(contentType);
            s3Client.putObject(bucketName, fileName, is, meta);
            return s3Client.getUrl(bucketName, fileName).toURI();
        } catch (URISyntaxException e) {
            throw  new RuntimeException("Erro ao converter URL para URI");
        }
    }
}
