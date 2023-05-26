package mlclover.appplication.services.admin.produtos;

import mlclover.appplication.dtos.admin.produtos.CorDTO;
import mlclover.appplication.entities.admin.produtos.Cor;
import mlclover.appplication.repositories.admin.produtos.CorRepository;
import mlclover.appplication.services.exceptions.EntityAlreadyExistsException;
import mlclover.appplication.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorService {

    @Autowired
    CorRepository repository;

    public List<CorDTO> cadastrarCor(CorDTO dto) {

        Cor cor = repository.findById(dto.getHexadecimal()).orElse(null);
        if(cor != null)
            throw new EntityAlreadyExistsException("Cor já existente");

        repository.save(converterCorDTOEmCor(dto));

        return repository.findAll().stream().map(obj -> converterCorEmCorDTO(obj)).toList();
    }

    public CorDTO converterCorEmCorDTO(Cor obj){
        return new CorDTO(obj.getHexadecimal(), obj.getNome());
    }


    public Cor converterCorDTOEmCor(CorDTO dto){
        Cor cor = new Cor();

        cor.setHexadecimal(dto.getHexadecimal());
        cor.setNome(dto.getNome());

        return cor;
    }

    public List<CorDTO> listaCores() {
        return repository.findAll().stream().map(cor -> converterCorEmCorDTO(cor)).toList();
    }

    protected Cor buscarCorPorHexadecimal(String hexadecimal){
        return repository.findById(hexadecimal).orElseThrow(()
                -> new EntityNotFoundException("Hexadecimal " + hexadecimal + " de cor não encontrada"));
    }
}
