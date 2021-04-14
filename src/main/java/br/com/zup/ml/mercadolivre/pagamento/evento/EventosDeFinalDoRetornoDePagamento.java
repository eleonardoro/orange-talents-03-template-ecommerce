package br.com.zup.ml.mercadolivre.pagamento.evento;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.ml.mercadolivre.compra.Compra;
import br.com.zup.ml.mercadolivre.compra.StatusDaCompra;
import br.com.zup.ml.mercadolivre.pagamento.Pagamento;
import br.com.zup.ml.mercadolivre.pagamento.evento.erro.EventoDeErroNoPagamento;
import br.com.zup.ml.mercadolivre.pagamento.evento.sucesso.EventoDeSucessoNoPagamento;

@Service
public class EventosDeFinalDoRetornoDePagamento {

	@Autowired
	private Set<EventoDeSucessoNoPagamento> eventosCompraSucesso;

	@Autowired
	private Set<EventoDeErroNoPagamento> eventosCompraErro;

	public void processa(Compra compra, Pagamento pagamento) {
		if (compra.getStatusDaCompra().equals(StatusDaCompra.FINALIZADA)) {
			eventosCompraSucesso.forEach(evento -> evento.processa(compra));
		} else {
			eventosCompraErro.forEach(evento -> evento.processa(compra, pagamento));
		}
	}
}