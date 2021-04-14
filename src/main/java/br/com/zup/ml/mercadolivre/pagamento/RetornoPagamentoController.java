package br.com.zup.ml.mercadolivre.pagamento;

import javax.persistence.Transient;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.ml.mercadolivre.compra.Compra;
import br.com.zup.ml.mercadolivre.compra.CompraRepository;
import br.com.zup.ml.mercadolivre.pagamento.evento.EventosDeFinalDoRetornoDePagamento;
import br.com.zup.ml.mercadolivre.pagamento.pagseguro.RetornoPagamentoPagseguroRequest;
import br.com.zup.ml.mercadolivre.pagamento.paypal.RetornoPagamentoPaypalRequest;

@RestController
@RequestMapping("/pagamentos")
public class RetornoPagamentoController {

	CompraRepository compraRepository;
	PagamentoRepository pagamentoRepository;
	EventosDeFinalDoRetornoDePagamento eventosDeFinalDoRetornoDePagamento;

	public RetornoPagamentoController(CompraRepository compraRepository, PagamentoRepository pagamentoRepository,
			EventosDeFinalDoRetornoDePagamento eventosDeFinalDoRetornoDePagamento) {
		this.compraRepository = compraRepository;
		this.pagamentoRepository = pagamentoRepository;
		this.eventosDeFinalDoRetornoDePagamento = eventosDeFinalDoRetornoDePagamento;
	}

	@PostMapping("/cria/paypal")
	public String criaRetornoPagamentoPaypal(
			@Valid @RequestBody RetornoPagamentoPaypalRequest retornoPagamentoPaypalRequest) {

		processaPagamento(retornoPagamentoPaypalRequest);

		return retornoPagamentoPaypalRequest.toString();
	}

	@PostMapping("/cria/pagseguro")
	public String criaRetornoPagamentoPagseguro(
			@Valid @RequestBody RetornoPagamentoPagseguroRequest retornoPagamentoPagseguroRequest) {

		processaPagamento(retornoPagamentoPagseguroRequest);

		return retornoPagamentoPagseguroRequest.toString();
	}

	@Transient
	private void processaPagamento(@Valid RetornoPagamentoRequest retornoPagamentoRequest) {
		Compra compra = compraRepository.findById(retornoPagamentoRequest.getIdCompra()).get();
		Pagamento pagamento = retornoPagamentoRequest.converterParaPagamento(compra);

		pagamentoRepository.save(pagamento);
		compra.addPagamento(pagamento);
		compraRepository.save(compra);

		eventosDeFinalDoRetornoDePagamento.processa(compra, pagamento);
	}
}