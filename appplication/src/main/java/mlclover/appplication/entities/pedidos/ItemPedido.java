package mlclover.appplication.entities.pedidos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mlclover.appplication.entities.admin.produtos.Produto;
import mlclover.appplication.entities.pedidos.ids.ItemPedidoId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_item_pedido")
public class ItemPedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId // Como é uma chave composta e usamos uma classe auxiliar para representar o ID, usamos o @EmbeddedID
    private ItemPedidoId id = new ItemPedidoId(); // Como é um objeto auxiliar precisa já ser instanciado

    private Double desconto;
    private Integer quantidade;
    private Double preco;

    public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
        this.id.setPedido(pedido);
        this.id.setProduto(produto);
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public double getSubTotal(){
        return (preco - desconto) * quantidade;
    }

    @JsonIgnore
    public Pedido getPedido(){
        return id.getPedido();
    }
    public void setPedido(Pedido pedido){
        id.setPedido(pedido);
    }
    public Produto getProduto(){
        return id.getProduto();
    }
    public void setProduto(Produto produto){
        id.setProduto(produto);
    }

    @Override
    public String toString(){
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        StringBuilder builder = new StringBuilder();
        builder.append(getProduto().getNome());
        builder.append(", Qte: ");
        builder.append(getQuantidade());
        builder.append(", Preço unitário: ");
        builder.append(nf.format(getPreco()));
        builder.append(", Subtotal: ");
        builder.append(nf.format(getPreco()));
        builder.append("\n");
        return builder.toString();
    }
}
