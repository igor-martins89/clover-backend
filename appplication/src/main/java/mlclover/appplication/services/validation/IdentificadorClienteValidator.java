package mlclover.appplication.services.validation;

import mlclover.appplication.controllers.exceptions.FieldMessage;
import mlclover.appplication.dtos.clientes.ClienteCadastroAdicionalDTO;
import mlclover.appplication.entities.clientes.enums.TipoCliente;
import mlclover.appplication.repositories.clientes.ClienteRepository;
import mlclover.appplication.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class IdentificadorClienteValidator implements ConstraintValidator<IdentificadorCliente, ClienteCadastroAdicionalDTO> {

    @Autowired
    private ClienteRepository repo;
    @Override
    public void initialize(IdentificadorCliente ann){
    }
    @Override
    public boolean isValid(ClienteCadastroAdicionalDTO objDto, ConstraintValidatorContext context){
        List<FieldMessage> list = new ArrayList<>();

        if(objDto.getTipo().equals(TipoCliente.PESSOA_FISICA.getCod()) &&
                !BR.isValidCPF(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }
        if(objDto.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCod()) &&
                !BR.isValidCNPJ(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
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
