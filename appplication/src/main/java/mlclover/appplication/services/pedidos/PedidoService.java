package mlclover.appplication.services.pedidos;

import mlclover.appplication.dtos.pedidos.PedidoDTO;
import mlclover.appplication.entities.pedidos.ItemPedido;
import mlclover.appplication.entities.pedidos.PagamentoComBoleto;
import mlclover.appplication.entities.pedidos.Pedido;
import mlclover.appplication.entities.pedidos.enums.EstadoPagamento;
import mlclover.appplication.repositories.pedidos.ItemPedidoRepository;
import mlclover.appplication.repositories.pedidos.PagamentoRepository;
import mlclover.appplication.repositories.pedidos.PedidoRepository;
import mlclover.appplication.services.admin.produtos.ProdutoService;
import mlclover.appplication.services.clientes.ClienteService;
import mlclover.appplication.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Stack;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmailService emailService;

    public PedidoDTO find(Integer id) {
        Pedido obj = repo.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
        return new PedidoDTO(obj);
    }

    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstante(new Date());
        obj.setCliente(clienteService.find(obj.getCliente().getId()));
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);

        if (obj.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
        }
        obj = repo.save(obj);
        pagamentoRepository.save(obj.getPagamento());
        for (ItemPedido ip : obj.getItens()) {
            ip.setDesconto(0.0);
            ip.setProduto(produtoService.find(ip.getProduto().getId()));
            ip.setPreco(ip.getProduto().getValor());
            ip.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItens());
        emailService.sendOrderConfirmationHtmlEmail(obj);
        return obj;
    }
}
