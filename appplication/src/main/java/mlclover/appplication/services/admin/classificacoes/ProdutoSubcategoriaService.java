package mlclover.appplication.services.admin.classificacoes;

import mlclover.appplication.entities.admin.classificacoes.CategoriaSubcategoria;
import mlclover.appplication.entities.admin.classificacoes.ProdutoSubcategoria;
import mlclover.appplication.repositories.admin.classificacoes.ProdutoSubcategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoSubcategoriaService {

    @Autowired
    ProdutoSubcategoriaRepository repository;

    public List<ProdutoSubcategoria> cadastrarListaDeRelacionamentos(List<ProdutoSubcategoria> lista) {

        List<ProdutoSubcategoria> listaProdutoSubcategoria = new ArrayList<>();
        for(ProdutoSubcategoria cs : lista){
            listaProdutoSubcategoria.add(repository.save(cs));
        }
        return listaProdutoSubcategoria;
    }
}
