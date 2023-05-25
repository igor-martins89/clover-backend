package mlclover.appplication.services.clientes;


import mlclover.appplication.dtos.clientes.CartaoDTO;
import mlclover.appplication.entities.clientes.Cartao;
import mlclover.appplication.entities.enums.Tipo;
import mlclover.appplication.repositories.clientes.CartaoRepository;
import mlclover.appplication.services.exceptions.AuthenticationCredentialsNotFoundException;
import mlclover.appplication.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartaoService {

    @Autowired
    CartaoRepository repository;

    @Autowired
    ClienteService clienteService;


    public List<CartaoDTO> cadastroCartao(CartaoDTO dto, int idCliente) {

        if(clienteService.isLogado(idCliente)){

            List<Cartao> lista = repository.findAllByClienteId(idCliente);

            for(Cartao e : lista){
                e.setTipoCartao(Tipo.ADITIONAL.getCod());
            }

            Cartao obj = converterCartaoDTOParaCartao(dto, idCliente);
            obj.setTipoCartao(Tipo.DEFAULT.getCod());

            lista.add(obj);

            lista = repository.saveAll(lista);

            return lista.stream().map(x -> converterCartaoParaCartaoDTO(x)).collect(Collectors.toList());
        }

        throw new AuthenticationCredentialsNotFoundException("Autenticação necessária, por favor faça o login para continuar");

    }

    protected Cartao converterCartaoDTOParaCartao(CartaoDTO dto, int idCliente){

      Cartao obj = new Cartao();

      obj.setTitular(dto.getTitular());
      obj.setNumero(dto.getNumero());
      obj.setUltimosQuatroDigitos(dto.getNumero().substring(12));
      obj.setDataVencimento(dto.getDataVencimento());
      obj.setCvv(dto.getCvv());

        return obj;
    }

    protected CartaoDTO converterCartaoParaCartaoDTO(Cartao obj){

        CartaoDTO dto = new CartaoDTO();

        dto.setId(obj.getId());
        dto.setTitular(obj.getTitular());
        dto.setNumero(obj.getNumero());
        dto.setUltimosQuatroDigitos(obj.getUltimosQuatroDigitos());
        dto.setDataVencimento(obj.getDataVencimento());
        dto.setCvv(obj.getCvv());
        dto.setTipoCartao(obj.getTipoCartao());

        return dto;
    }


    public List<CartaoDTO> atualizacaoCartao(CartaoDTO dto, int idCliente, int idCartao) {
        if(clienteService.isLogado(idCliente)){

            Cartao obj = repository.findById(idCartao).orElseThrow(()
                    -> new EntityNotFoundException("Id " + idCartao + " do cliente não encontrado"));

            Cartao objAtualizado = converterCartaoDTOParaCartao(dto, idCliente);
            objAtualizado.setId(obj.getId());
            objAtualizado.setTipoCartao(obj.getTipoCartao());

            repository.save(objAtualizado);

            return repository.findAllByClienteId(idCliente).stream().map(x ->  converterCartaoParaCartaoDTO(x)).collect(Collectors.toList());

        }
        throw new AuthenticationCredentialsNotFoundException("Autenticação necessária, por favor faça o login para continuar");

    }



    public List<CartaoDTO> atualizacaoCartaoPadrao(int idCliente, int idCartao) {

        if(clienteService.isLogado(idCliente)){
            Cartao obj = repository.findById(idCartao).orElseThrow(()
                    -> new EntityNotFoundException("Id " + idCartao + " do cliente não encontrado"));

            List<Cartao> lista = repository.findAllByClienteId(idCliente);

            for(Cartao e : lista){
                if(e.getId() == idCartao)
                    e.setTipoCartao(Tipo.DEFAULT.getCod());
                else
                    e.setTipoCartao(Tipo.ADITIONAL.getCod());
            }

            return repository.saveAll(lista).stream().map(x -> converterCartaoParaCartaoDTO(x)).collect(Collectors.toList());
        }
        throw new AuthenticationCredentialsNotFoundException("Autenticação necessária, por favor faça o login para continuar");

    }


    public List<CartaoDTO> deletarCartao(int idCliente, int idCartao) {
        if(clienteService.isLogado(idCliente)){

            Cartao obj = repository.findById(idCartao).orElseThrow(()
                    -> new EntityNotFoundException("Id " + idCartao + " do cliente não encontrado"));

            repository.deleteById(idCartao);

            List<Cartao> lista = repository.findAllByClienteId(idCliente);

            if(obj.getTipoCartao() == Tipo.DEFAULT.getCod() && !lista.isEmpty()){

                obj = lista.get(0);
                obj.setTipoCartao(Tipo.DEFAULT.getCod());

                repository.save(obj);

                lista = repository.findAllByClienteId(idCliente);
            }

            return lista.stream().map(x -> converterCartaoParaCartaoDTO(x)).collect(Collectors.toList());
        }

        throw new AuthenticationCredentialsNotFoundException("Autenticação necessária, por favor faça o login para continuar");
    }

    public List<CartaoDTO> buscaListaCartoesPorClienteId(int idCliente) {

        if(clienteService.isLogado(idCliente)){

            return repository.findAllByClienteId(idCliente).stream().map(x -> converterCartaoParaCartaoDTO(x)).collect(Collectors.toList());
        }

        throw new AuthenticationCredentialsNotFoundException("Autenticação necessária, por favor faça o login para continuar");

    }


}
