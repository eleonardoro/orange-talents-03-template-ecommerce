package br.com.zup.ml.mercadolivre.pagamento.evento.sucesso;

import br.com.zup.ml.mercadolivre.compra.Compra;

public interface EventoDeSucessoNoPagamento {
	void processa(Compra compra);
}
