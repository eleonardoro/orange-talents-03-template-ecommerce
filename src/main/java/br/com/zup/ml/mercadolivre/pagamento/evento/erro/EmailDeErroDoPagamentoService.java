package br.com.zup.ml.mercadolivre.pagamento.evento.erro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.ml.mercadolivre.compra.Compra;
import br.com.zup.ml.mercadolivre.pagamento.Pagamento;
import br.com.zup.ml.mercadolivre.services.EmailService;

@Service
public class EmailDeErroDoPagamentoService implements EventoDeErroNoPagamento {

	@Autowired
	EmailService emailService;

	@Override
	public void processa(Compra compra, Pagamento pagamento) {
		emailService.enviaEmailParaDonoDoProdutoParaNovoPagamentoComErro(compra, pagamento);
	}

}