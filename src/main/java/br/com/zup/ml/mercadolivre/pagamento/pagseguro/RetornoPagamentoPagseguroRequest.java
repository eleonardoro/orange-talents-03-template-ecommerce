package br.com.zup.ml.mercadolivre.pagamento.pagseguro;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.zup.ml.mercadolivre.compra.Compra;
import br.com.zup.ml.mercadolivre.pagamento.GatewayDePagamento;
import br.com.zup.ml.mercadolivre.pagamento.Pagamento;
import br.com.zup.ml.mercadolivre.pagamento.RetornoPagamentoRequest;
import br.com.zup.ml.mercadolivre.pagamento.StatusDePagamento;
import br.com.zup.ml.mercadolivre.validacoes.CompraNaoFinalizada;
import br.com.zup.ml.mercadolivre.validacoes.ExisteId;
import br.com.zup.ml.mercadolivre.validacoes.PagamentoJaRetornado;
import br.com.zup.ml.mercadolivre.validacoes.StatusPagseguroValido;

public class RetornoPagamentoPagseguroRequest implements @Valid RetornoPagamentoRequest {

	@NotNull
	@ExisteId(classe = Compra.class, atributo = "id")
	@CompraNaoFinalizada
	private UUID idCompra;

	@NotEmpty
	@PagamentoJaRetornado
	private String idPagamento;

	@NotEmpty
	@StatusPagseguroValido
	private String statusPagamento;

	public void setIdCompra(UUID idCompra) {
		this.idCompra = idCompra;
	}

	public void setIdPagamento(String idPagamento) {
		this.idPagamento = idPagamento;
	}

	public void setStatusPagamento(String statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

	@Override
	public UUID getIdCompra() {
		return idCompra;
	}

	@Override
	public String toString() {
		return "RetornoPagamentoPagseguroRequest [idCompra=" + idCompra + ", idPagamento=" + idPagamento
				+ ", statusPagamento=" + statusPagamento + "]";
	}

	@Override
	public Pagamento converterParaPagamento(Compra compra) {
		StatusDePagamento statusDePagamento = statusPagamento.equalsIgnoreCase(StatusDePagamento.SUCESSO.toString())
				? StatusDePagamento.SUCESSO
				: StatusDePagamento.ERRO;

		return new Pagamento(compra, idPagamento, GatewayDePagamento.PAGSEGURO, statusDePagamento);
	}

}