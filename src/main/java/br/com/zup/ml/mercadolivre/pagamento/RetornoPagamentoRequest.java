package br.com.zup.ml.mercadolivre.pagamento;

import java.util.UUID;

import br.com.zup.ml.mercadolivre.compra.Compra;

public interface RetornoPagamentoRequest {

	UUID getIdCompra();

	Pagamento converterParaPagamento(Compra compra);

}