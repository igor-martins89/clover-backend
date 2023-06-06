package mlclover.appplication.entities.pedidos;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mlclover.appplication.entities.clientes.Cartao;
import mlclover.appplication.entities.pedidos.enums.EstadoPagamento;
import mlclover.appplication.entities.pedidos.abstracts.Pagamento;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Setter
@Entity
@JsonTypeName("pagamentoComCartao")
@Table(name = "tb_pagamento_com_cartao")
public class PagamentoComCartao extends Pagamento {
    private static final long serialVersionUID = 1L;

    private Integer numeroDeParcelas;
    @OneToOne
    @JoinColumn(name = "cartao_id")
    private Cartao cartao;

    public PagamentoComCartao(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numeroDeParcelas){
        super(id, estadoPagamento, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }




}
