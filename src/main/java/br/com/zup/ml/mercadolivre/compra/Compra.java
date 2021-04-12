package br.com.zup.ml.mercadolivre.compra;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.zup.ml.mercadolivre.produto.Produto;
import br.com.zup.ml.mercadolivre.usuario.Usuario;

@Entity
public class Compra {

	@Id
	private final UUID id = UUID.randomUUID();

	@ManyToOne(optional = false)
	private Produto produto;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private FormaDePagamento formaDePagamento;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusDaCompra statusDaCompra = StatusDaCompra.INICIADA;

	@Column(nullable = false)
	private Integer quantidade;

	@ManyToOne(optional = false)
	private Usuario comprador;

	@Column(nullable = false)
	private BigDecimal valorDaCompra;

	@Deprecated
	public Compra() {
	}

	public Compra(Produto produto, FormaDePagamento formaDePagamento, Integer quantidade, Usuario comprador,
			BigDecimal valorDaCompra) {
		this.produto = produto;
		this.formaDePagamento = formaDePagamento;
		this.quantidade = quantidade;
		this.comprador = comprador;
		this.valorDaCompra = valorDaCompra;
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", produto=" + produto.getNome() + ", formaDePagamento=" + formaDePagamento.name()
				+ ", statusDaCompra=" + statusDaCompra.name() + ", quantidade=" + quantidade + ", comprador="
				+ comprador.getEmail() + ", valorDaCompra=" + valorDaCompra + "]";
	}
	
	public UUID getId() {
		return id;
	}

	public Produto getProduto() {
		return produto;
	}

	public FormaDePagamento getFormaDePagamento() {
		return formaDePagamento;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Usuario getComprador() {
		return comprador;
	}

	public BigDecimal getValorDaCompra() {
		return valorDaCompra;
	}

}