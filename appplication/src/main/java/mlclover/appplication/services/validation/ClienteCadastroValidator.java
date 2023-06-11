package mlclover.appplication.services.validation;

import mlclover.appplication.controllers.exceptions.FieldMessage;
import mlclover.appplication.dtos.clientes.ClienteCadastroInicialDTO;
import mlclover.appplication.entities.clientes.Cliente;
import mlclover.appplication.repositories.clientes.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteCadastroValidator implements ConstraintValidator<ClienteCadastro, ClienteCadastroInicialDTO> {

    @Autowired
    private ClienteRepository repo;
    @Override
    public void initialize(ClienteCadastro ann){
    }
    @Override
    public boolean isValid(ClienteCadastroInicialDTO objDto, ConstraintValidatorContext context){
        List<FieldMessage> list = new ArrayList<>();

        Cliente aux = repo.findByEmail(objDto.getEmail()).orElse(null);
        if(aux != null){
            list.add(new FieldMessage("email", "Email já existente"));
        }

        /** testes aqui, inserindo erros na lista */

        for(FieldMessage e : list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
