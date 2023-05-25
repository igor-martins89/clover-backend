package mlclover.appplication.services.clientes;

import mlclover.appplication.dtos.clientes.ClienteCadastroAdicionalDTO;
import mlclover.appplication.dtos.clientes.ClienteCadastroInicialDTO;
import mlclover.appplication.dtos.clientes.ClienteDTO;
import mlclover.appplication.dtos.clientes.ClienteLoginDTO;
import mlclover.appplication.entities.clientes.Cliente;
import mlclover.appplication.repositories.clientes.ClienteRepository;
import mlclover.appplication.services.exceptions.AuthenticationCredentialsNotFoundException;
import mlclover.appplication.services.exceptions.BadCredentialsException;
import mlclover.appplication.services.exceptions.EntityNotFoundException;
import mlclover.appplication.services.exceptions.UsernameNotFoundException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository repository;

    /**
      Codifica string na base 64 (Encoder)
     */
    public static String codificaBase64Encoder(String msg) {
        return new Base64().encodeToString(msg.getBytes());
    }

    /**
      Decodifica string na base 64 (Decoder)
     */
    public static String decodificaBase64Decoder(String msg) {
        return new String(new Base64().decode(msg));
    }


    public ClienteCadastroInicialDTO cadastroInicial(ClienteCadastroInicialDTO objDTO) {
        objDTO.setSenha(codificaBase64Encoder(objDTO.getSenha()));
        objDTO.setId(repository.save(converterCadastroInicialDTOParaEntity(objDTO)).getId());
        return objDTO;
    }

    public void cadastroAdicional(ClienteCadastroAdicionalDTO dto, int id) {
        Cliente cliente = encontrarPorId(id);
        if(isLogado(id)){
            cliente.setDataNascimento(dto.getDataNascimento());
            cliente.setCpfOuCnpj(dto.getCpfOuCnpj());
            repository.save(cliente);
            return;
        }
        throw new AuthenticationCredentialsNotFoundException("Autenticação necessária, por favor faça o login para continuar");

    }

    private Cliente converterCadastroInicialDTOParaEntity(ClienteCadastroInicialDTO dto){
        Cliente obj = new Cliente();

        obj.setNome(dto.getNome());
        obj.setEmail(dto.getEmail());
        obj.setSenha(dto.getSenha());
        obj.setGenero(dto.getGenero());
        obj.setTelefone(dto.getTelefone());

        return obj;
    }

    protected Cliente encontrarPorId(int id){

        Cliente cli = repository.findById(id).orElse(null);

        if(cli != null)
            return cli;
        else{
            throw new EntityNotFoundException("Id " + id + " do cliente não encontrado");
        }
    }

    public ClienteDTO login(ClienteLoginDTO objDTO) {
        Cliente cli = repository.findByEmail(objDTO.getEmail()).orElseThrow(()
                -> new UsernameNotFoundException("E-mail não encontrado: " + objDTO.getEmail()));

        if(decodificaBase64Decoder(cli.getSenha()).equals(objDTO.getSenha())){
            cli.setLogado(true);
            repository.save(cli);
            return converteClienteParaClienteDTO(cli);
        }
        throw new BadCredentialsException("Credenciais inválidas");
    }

    private ClienteDTO converteClienteParaClienteDTO(Cliente obj){
        ClienteDTO dto = new ClienteDTO();
        dto.setId(obj.getId());
        dto.setNome(obj.getNome());
        dto.setEmail(obj.getEmail());
        dto.setTelefone(obj.getTelefone());
        dto.setGenero(obj.getGenero());

        if(obj.getDataNascimento() != null){
            dto.setDataNascimento(obj.getDataNascimento());
        }

        if(obj.getCpfOuCnpj() != null){
            dto.setCpfOuCnpj(obj.getCpfOuCnpj());
        }

        return dto;
    }

    protected boolean isLogado(int id){
        return encontrarPorId(id).isLogado();
    }

    public void logoff(int id) {
        Cliente obj = encontrarPorId(id);
        obj.setLogado(false);
        repository.save(obj);
    }

    public ClienteDTO buscaPorId(int id) {
        if(isLogado(id)){
            return converteClienteParaClienteDTO(encontrarPorId(id));
        }
        throw new AuthenticationCredentialsNotFoundException("Autenticação necessária, por favor faça o login para continuar");
    }
}
