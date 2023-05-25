package mlclover.appplication.services.admin.classificacoes;

import mlclover.appplication.dtos.admin.classificacoes.CategoriaDTO;
import mlclover.appplication.dtos.admin.classificacoes.ColecaoDTO;
import mlclover.appplication.dtos.admin.classificacoes.SubcategoriaDTO;
import mlclover.appplication.dtos.admin.classificacoes.SubcategoriaResponseDTO;
import mlclover.appplication.entities.admin.classificacoes.*;
import mlclover.appplication.entities.admin.classificacoes.ids.CategoriaSubcategoriaId;
import mlclover.appplication.entities.admin.classificacoes.ids.ColecaoCategoriaId;
import mlclover.appplication.repositories.admin.classificacoes.SubcategoriaRepository;
import mlclover.appplication.services.exceptions.CategoriaSemAssociacaoException;
import mlclover.appplication.services.exceptions.EntityAlreadyExistsException;
import mlclover.appplication.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SubcategoriaService {

    @Autowired
    SubcategoriaRepository repository;

    @Autowired
    ColecaoService colecaoService;

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    CategoriaSubcategoriaService categoriaSubcategoriaService;

    public List<Subcategoria> buscaListaSubcategoriasPorId(List<Integer> ids) {
        List<Subcategoria> lista = new ArrayList<>();

        for(Integer i : ids){
            Subcategoria subcategoria = repository.findById(i).orElseThrow(()
                    -> new EntityNotFoundException("Id " + i + " de subcategoria não encontrado"));
            lista.add(subcategoria);
        }
        return lista;
    }

    public Subcategoria subcategoriaExists(String nome){
        return repository.findByNome(nome);
    }

    public Subcategoria subcategoriaExists(int id){
        return repository.findById(id).orElse(null);
    }

    public List<SubcategoriaDTO> cadastrarSubcategoria(SubcategoriaDTO dto) {


        if(subcategoriaExists(dto.getNome()) != null)
            throw new EntityAlreadyExistsException("Subcategoria " + dto.getNome() + " já existe");

        categoriaService.hasListaCategorias(dto.getCategorias());

        Subcategoria subcategoria = converterSubcategoriaDTOEmSubcategoria(dto);

        /**
        Salva a Subcategoria no banco de dados para gerar um ID
         */
        subcategoria = repository.save(subcategoria);

        /**
        Cria as associações entre a Subcategoria com Colecoes e Subcategoria com Categorias  usando o ID gerado
        */

        List<CategoriaSubcategoria> lista = organizarRelacionamentosDeSubcategoria(subcategoria, dto);

        lista = categoriaSubcategoriaService.cadastrarListaDeRelacionamentos(lista);
        subcategoria.setCategoriaSubcategorias(lista);
        List<Subcategoria> listaSubcategorias = repository.findAll();

         /**
        Adiciona as coleções e as categorias associadas à nova subcategoria à lista de subcategorias
         */


        return converterListaSubcategoriaEmListaSubcategoriaDTO(listaSubcategorias);


    }

    private List<SubcategoriaDTO> converterListaSubcategoriaEmListaSubcategoriaDTO(List<Subcategoria> subcategorias) {
        return subcategorias.stream().map(subcategoria -> converterSubcategoriaEmSubcategoriaDTO(subcategoria)).collect(Collectors.toList());
    }

    public SubcategoriaDTO converterSubcategoriaEmSubcategoriaDTO(Subcategoria obj) {

        SubcategoriaDTO dto = new SubcategoriaDTO();

        dto.setId(obj.getId());
        dto.setNome(obj.getNome());





        List<CategoriaDTO> categoriasDTO = obj.getCategoriaSubcategorias().stream()
                .map(CategoriaSubcategoria::getColecaoCategoriaId)
                .map(ColecaoCategoria::getCategoriaId)
                .distinct() // Adicionado método distinct() para evitar duplicatas
                .map(categoriaService::converterCategoriaEmCategoriaDTO)
                .collect(Collectors.toList());



        dto.setCategorias(categoriasDTO);
        return dto;

    }

    private List<CategoriaSubcategoria> organizarRelacionamentosDeSubcategoria(Subcategoria subcategoria, SubcategoriaDTO dto) {

    if(dto.getCategorias().isEmpty())
            throw new CategoriaSemAssociacaoException("A subcategoria não tem associação com nenhuma categoria");

        List<CategoriaSubcategoria> listaCategoriaSubcategoria = new ArrayList<>();

        for(CategoriaDTO categoriaDTO : dto.getCategorias()){
            Categoria categoria = categoriaService.converterCategoriaDTOEmCategoria(categoriaDTO);
            for(ColecaoDTO colecaoDTO : categoriaDTO.getColecoes()){
                Colecao colecao = colecaoService.converterColecaoDTOEmColecao(colecaoDTO);
                CategoriaSubcategoria categoriaSubcategoria =
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
                                subcategoria);

                listaCategoriaSubcategoria.add(categoriaSubcategoria);
            }
        }
        return listaCategoriaSubcategoria;
    }

    public Subcategoria converterSubcategoriaDTOEmSubcategoria(SubcategoriaDTO dto) {

        Subcategoria obj = new Subcategoria();
        if(dto.getId() != null)
            obj.setId(dto.getId());

        obj.setNome(dto.getNome());
        return obj;
    }

    public boolean hasListaSubcategorias(List<SubcategoriaDTO> listaDTO){
        System.out.println("CHEGA AQUI 10");
        List<Subcategoria> lista = listaDTO.stream().map(x -> converterSubcategoriaDTOEmSubcategoria(x)).toList();

        System.out.println("CHEGA AQUI 11");
        for(Subcategoria obj : lista){
            System.out.println(obj.getId());
            repository.findById(obj.getId()).orElseThrow(()
                    -> new EntityNotFoundException("Id " + obj.getId() + " de subcategoria não encontrado"));
        }
        System.out.println("CHEGA AQUI 12");
        return true;
    }

    public Set<SubcategoriaResponseDTO> converterListaSubcategoriaEmListaSubcategoriaResponseDTO(Set<Subcategoria> listaSubcategoria) {

        return listaSubcategoria.stream().map(subcategoria  -> converterSubcategoriaEmSubcategoriaResponseDTO(subcategoria)).collect(Collectors.toSet());

    }

    private SubcategoriaResponseDTO converterSubcategoriaEmSubcategoriaResponseDTO(Subcategoria subcategoria){
        return new SubcategoriaResponseDTO(subcategoria.getId(), subcategoria.getNome());
    }
}
