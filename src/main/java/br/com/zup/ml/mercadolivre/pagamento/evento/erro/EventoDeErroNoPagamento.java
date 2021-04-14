package br.com.zup.ml.mercadolivre.pagamento.evento.erro;

import br.com.zup.ml.mercadolivre.compra.Compra;
import br.com.zup.ml.mercadolivre.pagamento.Pagamento;

public interface EventoDeErroNoPagamento {
	void processa(Compra compra, Pagamento pagamento);
}
