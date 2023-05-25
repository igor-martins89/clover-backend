package mlclover.appplication.services.admin.classificacoes;

import mlclover.appplication.entities.admin.classificacoes.CategoriaSubcategoria;
import mlclover.appplication.entities.admin.classificacoes.ids.ColecaoCategoriaId;
import mlclover.appplication.repositories.admin.classificacoes.CategoriaSubcategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaSubcategoriaService {

    @Autowired
    CategoriaSubcategoriaRepository repository;

    @Autowired
    ColecaoCategoriaService colecaoCategoriaService;

    public List<CategoriaSubcategoria> cadastrarListaDeRelacionamentos(List<CategoriaSubcategoria> lista) {

        List<CategoriaSubcategoria> listaCategoriaSubcategoria = new ArrayList<>();
        for(CategoriaSubcategoria cs : lista){
            listaCategoriaSubcategoria.add(repository.save(cs));
        }
        return listaCategoriaSubcategoria;
    }
}
