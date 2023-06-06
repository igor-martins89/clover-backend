package mlclover.appplication.services.clientes;

import mlclover.appplication.dtos.clientes.EnderecoDTO;
import mlclover.appplication.entities.clientes.Cliente;
import mlclover.appplication.entities.clientes.Endereco;
import mlclover.appplication.entities.clientes.enums.Tipo;
import mlclover.appplication.repositories.clientes.EnderecoRepository;
import mlclover.appplication.services.exceptions.AuthenticationCredentialsNotFoundException;
import mlclover.appplication.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository repository;

    @Autowired
    ClienteService clienteService;

    public List<EnderecoDTO> cadastroEndereco(EnderecoDTO dto, int idCliente) {

        if(clienteService.isLogado(idCliente)){

            List<Endereco> lista = repository.findAllByClienteId(idCliente);

            for(Endereco e : lista){
                e.setTipoEndereco(Tipo.ADITIONAL.getCod());
            }

            Endereco obj = converterEnderecoDTOParaEndereco(dto, idCliente);
            obj.setTipoEndereco(Tipo.DEFAULT.getCod());

            lista.add(obj);

            lista = repository.saveAll(lista);

            return lista.stream().map(x -> converterEnderecoParaEnderecoDTO(x)).collect(Collectors.toList());
        }

        throw new AuthenticationCredentialsNotFoundException("Autenticação necessária, por favor faça o login para continuar");

    }

    private Endereco converterEnderecoDTOParaEndereco(EnderecoDTO dto, int idCliente){

        Endereco obj = new Endereco();
        obj.setCep(dto.getCep());
        obj.setRua(dto.getRua());
        obj.setNumero(dto.getNumero());
        obj.setComplemento(dto.getComplemento());
        obj.setBairro(dto.getBairro());
        obj.setCidade(dto.getCidade());
        obj.setEstado(dto.getEstado());
        obj.setDestinatario(dto.getDestinatario());

        Cliente cli = clienteService.encontrarPorId(idCliente);

        obj.setCliente(cli);

        return obj;

    }

    private EnderecoDTO converterEnderecoParaEnderecoDTO(Endereco obj){
        EnderecoDTO dto = new EnderecoDTO();

        dto.setId(obj.getId());
        dto.setCep(obj.getCep());
        dto.setRua(obj.getRua());
        dto.setNumero(obj.getNumero());
        dto.setComplemento(obj.getComplemento());
        dto.setBairro(obj.getBairro());
        dto.setCidade(obj.getCidade());
        dto.setEstado(obj.getEstado());
        dto.setDestinatario(obj.getDestinatario());
        dto.setTipoEndereco(Tipo.toEnum(obj.getTipoEndereco()));

        return dto;
    }

    public List<EnderecoDTO> atualizacaoEndereco(EnderecoDTO dto, int idCliente, int idEndereco) {
        if(clienteService.isLogado(idCliente)){

            Endereco obj = repository.findById(idEndereco).orElseThrow(()
                    -> new EntityNotFoundException("Id " + idEndereco + " do cliente não encontrado"));

            Endereco objAtualizado = converterEnderecoDTOParaEndereco(dto, idCliente);
            objAtualizado.setId(obj.getId());
            objAtualizado.setTipoEndereco(obj.getTipoEndereco());

            repository.save(objAtualizado);

            return repository.findAllByClienteId(idCliente).stream().map(x ->  converterEnderecoParaEnderecoDTO(x)).collect(Collectors.toList());

        }
        throw new AuthenticationCredentialsNotFoundException("Autenticação necessária, por favor faça o login para continuar");

    }

    public List<EnderecoDTO> atualizacaoEnderecoPadrao(int idCliente, int idEndereco) {

        if(clienteService.isLogado(idCliente)){
            Endereco obj = repository.findById(idEndereco).orElseThrow(()
                    -> new EntityNotFoundException("Id " + idEndereco + " do cliente não encontrado"));

            List<Endereco> lista = repository.findAllByClienteId(idCliente);

            for(Endereco e : lista){
                if(e.getId() == idEndereco)
                    e.setTipoEndereco(Tipo.DEFAULT.getCod());
                else
                e.setTipoEndereco(Tipo.ADITIONAL.getCod());
            }

            return repository.saveAll(lista).stream().map(x -> converterEnderecoParaEnderecoDTO(x)).collect(Collectors.toList());
        }
        throw new AuthenticationCredentialsNotFoundException("Autenticação necessária, por favor faça o login para continuar");

    }

    public List<EnderecoDTO> deletarEndereco(int idCliente, int idEndereco) {
        if(clienteService.isLogado(idCliente)){

            Endereco obj = repository.findById(idEndereco).orElseThrow(()
                    -> new EntityNotFoundException("Id " + idEndereco + " do cliente não encontrado"));

            repository.deleteById(idEndereco);

            List<Endereco> lista = repository.findAllByClienteId(idCliente);

            if(obj.getTipoEndereco() == Tipo.DEFAULT.getCod() && !lista.isEmpty()){

                obj = lista.get(0);
                obj.setTipoEndereco(Tipo.DEFAULT.getCod());

                repository.save(obj);

                lista = repository.findAllByClienteId(idCliente);
            }

            return lista.stream().map(x -> converterEnderecoParaEnderecoDTO(x)).collect(Collectors.toList());
        }

        throw new AuthenticationCredentialsNotFoundException("Autenticação necessária, por favor faça o login para continuar");
    }

    public List<EnderecoDTO> buscaListaEnderecosPorClienteId(int idCliente) {

        if(clienteService.isLogado(idCliente)){

            return repository.findAllByClienteId(idCliente).stream().map(x -> converterEnderecoParaEnderecoDTO(x)).collect(Collectors.toList());
        }

        throw new AuthenticationCredentialsNotFoundException("Autenticação necessária, por favor faça o login para continuar");

    }
}
