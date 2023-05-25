package mlclover.appplication.services.admin.classificacoes;

import mlclover.appplication.entities.admin.classificacoes.ColecaoCategoria;
import mlclover.appplication.repositories.admin.classificacoes.ColecaoCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColecaoCategoriaService {

    @Autowired
    ColecaoCategoriaRepository repository;


    public List<ColecaoCategoria> cadastrarListaDeRelacionamentos(List<ColecaoCategoria> lista){
        return repository.saveAll(lista);
    }
}
