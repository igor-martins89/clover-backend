package mlclover.appplication.services.admin.classificacoes;

import mlclover.appplication.dtos.admin.classificacoes.CategoriaDTO;
import mlclover.appplication.dtos.admin.classificacoes.ColecaoDTO;
import mlclover.appplication.entities.admin.classificacoes.Categoria;
import mlclover.appplication.entities.admin.classificacoes.Colecao;
import mlclover.appplication.entities.admin.classificacoes.ColecaoCategoria;
import mlclover.appplication.entities.admin.classificacoes.ids.ColecaoCategoriaId;
import mlclover.appplication.repositories.admin.classificacoes.CategoriaRepository;
import mlclover.appplication.services.exceptions.CategoriaSemAssociacaoException;
import mlclover.appplication.services.exceptions.EntityAlreadyExistsException;
import mlclover.appplication.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository repository;

    @Autowired
    ColecaoService colecaoService;

    @Autowired
    ColecaoCategoriaService colecaoCategoriaService;

    public List<Categoria> buscaListaCategoriasPorId(List<Integer> ids) {
        List<Categoria> lista = new ArrayList<>();

        for(Integer i : ids){
            Categoria categoria = repository.findById(i).orElseThrow(()
                    -> new EntityNotFoundException("Id " + i + " de categoria não encontrado"));
            lista.add(categoria);
        }
        return lista;
    }

    public Categoria categoriaExists(String nome){
        return repository.findByNome(nome);
    }

    public Categoria categoriaExists(int id){
        return repository.findById(id).orElse(null);
    }

    public List<CategoriaDTO> cadastrarCategoria(CategoriaDTO dto) {

        if(categoriaExists(dto.getNome()) != null)
            throw new EntityAlreadyExistsException("Categoria " + dto.getNome() + " já existe");

        colecaoService.hasListaColecoes(dto.getColecoes());

        Categoria categoria = converterCategoriaDTOEmCategoria(dto);


       /**
       Salva a Categoria no banco de dados para gerar um ID
        */

        categoria = repository.save(categoria);

        /**
        Cria as associações entre a Categoria e as Colecoes usando o ID gerado
         */

        List<ColecaoCategoria> lista = organizarRelacionamentosDeCategoriaComColecao(categoria, dto);
        /**
        Salva as associações com as Colecoes
         */

        lista = colecaoCategoriaService.cadastrarListaDeRelacionamentos(lista);
        categoria.setColecaoCategorias(lista);

        List<Categoria> listaCategorias = repository.findAll();


        /**
        Adiciona as coleções associadas à nova categoria à lista de categorias
         */

        Categoria categoriaStream = categoria;
        listaCategorias.stream()
                .filter(c -> c.getId() == categoriaStream.getId())
                .forEach(c -> c = categoriaStream);

        return converterListaCategoriaEmListaCategoriaDTO(listaCategorias);
    }

    private List<CategoriaDTO> converterListaCategoriaEmListaCategoriaDTO(List<Categoria> categorias) {
        return categorias.stream().map(cat -> converterCategoriaEmCategoriaDTO(cat)).collect(Collectors.toList());
    }

    private List<ColecaoCategoria> organizarRelacionamentosDeCategoriaComColecao(Categoria categoria, CategoriaDTO dto) {
        if(dto.getColecoes().isEmpty())
            throw new CategoriaSemAssociacaoException("A categoria não tem associação com nenhuma coleção");
        List<ColecaoCategoria> listaColecaoCategoria = new ArrayList<>();

        for(ColecaoDTO colecaoDTO : dto.getColecoes()){
            Colecao colecao = colecaoService.converterColecaoDTOEmColecao(colecaoDTO);
            listaColecaoCategoria.add(new ColecaoCategoria(new ColecaoCategoriaId(colecao.getId(), categoria.getId()), colecao, categoria));
        }
        return listaColecaoCategoria;
    }

    public Categoria converterCategoriaDTOEmCategoria(CategoriaDTO dto) {
        Categoria obj = new Categoria();
        if(dto.getId() != null){
            obj.setId(dto.getId());
        }
        obj.setNome(dto.getNome());
        return obj;
    }

    protected CategoriaDTO converterCategoriaEmCategoriaDTO(Categoria obj){
        CategoriaDTO dto = new CategoriaDTO();

        dto.setId(obj.getId());
        dto.setNome(obj.getNome());

        List<ColecaoDTO> colecoesDTO = obj.getColecaoCategorias().stream()
                        .map(ColecaoCategoria::getColecaoId)
                                .map(colecaoService::converterColecaoEmColecaoDTO)
                                        .collect(Collectors.toList());

        dto.setColecoes(colecoesDTO);
        return dto;
    }

    public boolean hasListaCategorias(List<CategoriaDTO> listaDTO){
        List<Categoria> lista = listaDTO.stream().map(x -> converterCategoriaDTOEmCategoria(x)).toList();

        for(Categoria obj : lista){
            repository.findById(obj.getId()).orElseThrow(()
                    -> new EntityNotFoundException("Id " + obj.getId() + " de categoria não encontrado"));
        }
        return true;
    }
}
