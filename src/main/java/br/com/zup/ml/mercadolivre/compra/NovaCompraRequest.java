package br.com.zup.ml.mercadolivre.compra;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.ml.mercadolivre.produto.Produto;
import br.com.zup.ml.mercadolivre.produto.ProdutoRepository;
import br.com.zup.ml.mercadolivre.usuario.Usuario;
import br.com.zup.ml.mercadolivre.validacoes.ExisteFormaDePagamento;
import br.com.zup.ml.mercadolivre.validacoes.ExisteId;
import br.com.zup.ml.mercadolivre.validacoes.TemQuantidadeDisponivel;

@TemQuantidadeDisponivel
public class NovaCompraRequest {

	@NotNull
	@Positive
	@ExisteId(classe = Produto.class, atributo = "id")
	private Long idProduto;

	@NotNull
	@Positive
	private Integer quantidade;

	@NotNull
	@ExisteFormaDePagamento
	private String formaDePagamento;

	public NovaCompraRequest(@NotNull @Positive Long idProduto, @NotNull @Positive Integer quantidade,
			@NotNull String formaDePagamento) {
		this.idProduto = idProduto;
		this.quantidade = quantidade;
		this.formaDePagamento = formaDePagamento;
	}

	@Override
	public String toString() {
		return "NovaCompraRequest [idProduto=" + idProduto + ", quantidade=" + quantidade + ", formaDePagamento="
				+ formaDePagamento + "]";
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public String getFormaDePagamento() {
		return formaDePagamento;
	}

	public Compra converterParaCompra(ProdutoRepository produtoRepository, Usuario comprador) {
		Produto produto = produtoRepository.findById(idProduto).get();

		return new Compra(produto, 
				FormaDePagamento.valueOf(formaDePagamento.toUpperCase()), 
				quantidade, 
				comprador,
				(produto.getValor().multiply(BigDecimal.valueOf(quantidade))));
	}
}