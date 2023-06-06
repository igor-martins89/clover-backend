package mlclover.appplication.entities.pedidos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mlclover.appplication.entities.pedidos.enums.EstadoPagamento;
import mlclover.appplication.entities.pedidos.abstracts.Pagamento;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@JsonTypeName("pagamentoComBoleto")
@Table(name = "tb_pagamento_com_boleto")
public class PagamentoComBoleto extends Pagamento {
    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataVencimento;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataPagamento;

    public PagamentoComBoleto(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Date dataVencimento, Date dataPagamento){
        super(id, estadoPagamento, pedido);
        this.dataPagamento = dataPagamento;
        this.dataVencimento = dataVencimento;
    }
}
