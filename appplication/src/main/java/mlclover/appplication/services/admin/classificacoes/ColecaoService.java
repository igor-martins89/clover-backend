package mlclover.appplication.services.admin.classificacoes;

import mlclover.appplication.dtos.admin.classificacoes.CategoriaResponseDTO;
import mlclover.appplication.dtos.admin.classificacoes.ColecaoDTO;
import mlclover.appplication.dtos.admin.classificacoes.ColecaoResponseDTO;
import mlclover.appplication.dtos.admin.classificacoes.SubcategoriaResponseDTO;
import mlclover.appplication.entities.admin.classificacoes.Categoria;
import mlclover.appplication.entities.admin.classificacoes.Colecao;
import mlclover.appplication.entities.admin.classificacoes.Subcategoria;
import mlclover.appplication.repositories.admin.classificacoes.ColecaoRepository;

import mlclover.appplication.services.exceptions.CategoriaSemAssociacaoException;
import mlclover.appplication.services.exceptions.EntityAlreadyExistsException;
import mlclover.appplication.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class ColecaoService {

    @Autowired
    ColecaoRepository repository;

    @Autowired
    SubcategoriaService subcategoriaService;

    public List<ColecaoDTO> cadastroColecao(ColecaoDTO dto) {

        if(colecaoExists(dto.getNome()) != null)
            throw new EntityAlreadyExistsException("Coleção " + dto.getNome() + " já existe");

        Colecao colecao = converterColecaoDTOEmColecao(dto);
        repository.save(colecao);

        return converterListaColecaoEmListaColecaoDTO(repository.findAll());

    }

    public Colecao colecaoExists(String nome){
        return repository.findByNome(nome);
    }

    public Colecao colecaoExists(int id){return repository.findById(id).orElse(null);}
    protected List<ColecaoDTO> converterListaColecaoEmListaColecaoDTO(List<Colecao> colecoes){
        return colecoes.stream().map(col -> converterColecaoEmColecaoDTO(col)).collect(Collectors.toList());
    }

    protected ColecaoDTO converterColecaoEmColecaoDTO(Colecao obj){
        return new ColecaoDTO(obj.getId(), obj.getNome());
    }

    public Colecao converterColecaoDTOEmColecao(ColecaoDTO dto) {
        Colecao obj = new Colecao();
        if(dto.getId() != null)
            obj.setId(dto.getId());

        obj.setNome(dto.getNome());
        return obj;
    }

    public boolean hasListaColecoes(List<ColecaoDTO> listaDTO){
        List<Colecao> lista = listaDTO.stream().map(colecao-> converterColecaoDTOEmColecao(colecao)).toList();

        for(Colecao obj : lista){
            repository.findById(obj.getId()).orElseThrow(()
                    -> new EntityNotFoundException("Id " + obj.getId() + " de coleção não encontrado"));
        }

        return true;
    }

    private List<ColecaoResponseDTO> converterColecaoEmColecaoResponseDTO(List<Colecao> lista){
        List<ColecaoResponseDTO> listaColecaoDTO = new ArrayList<>();

        for(Colecao colecao : lista){
            Set<CategoriaResponseDTO> listaCategoriaDTO = new HashSet<>();

            Set<Categoria> listaCategorias = colecao.getColecaoCategorias().stream().map(colecaoCategoria ->  colecaoCategoria.getCategoriaId()).collect(Collectors.toSet());

            for(Categoria categoria : listaCategorias){


                Set<Subcategoria> listaSubcategoria = categoria.getColecaoCategorias()
                        .stream()
                        .flatMap(colecaoCategoria -> colecaoCategoria.getProdutos().stream())
                        .map(subcategoria -> subcategoria.getSubcategoriaId())
                        .collect(Collectors.toSet());

                Set<SubcategoriaResponseDTO> listaSubcategoriaDTO = subcategoriaService.converterListaSubcategoriaEmListaSubcategoriaResponseDTO(listaSubcategoria);

                listaCategoriaDTO.add(new CategoriaResponseDTO(
                        categoria.getId(),
                        categoria.getNome(),
                        listaSubcategoriaDTO.stream().toList()
                ));
            }

            listaColecaoDTO.add(new ColecaoResponseDTO(
                    colecao.getId(),
                    colecao.getNome(),
                    listaCategoriaDTO.stream().toList()
            ));
        }

        return listaColecaoDTO;
    }

    public List<ColecaoResponseDTO> listaColecoes() {
        List<Colecao> lista =  repository.findAll();

        return converterColecaoEmColecaoResponseDTO(lista);
    }
}
