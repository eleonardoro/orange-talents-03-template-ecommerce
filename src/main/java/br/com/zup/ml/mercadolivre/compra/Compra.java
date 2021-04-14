package br.com.zup.ml.mercadolivre.compra;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

import br.com.zup.ml.mercadolivre.pagamento.Pagamento;
import br.com.zup.ml.mercadolivre.pagamento.StatusDePagamento;
import br.com.zup.ml.mercadolivre.produto.Produto;
import br.com.zup.ml.mercadolivre.usuario.Usuario;

@Entity
public class Compra {

	@Id
	@Type(type = "uuid-char")
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

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Usuario comprador;

	@Column(nullable = false)
	private BigDecimal valorDaCompra;

	@OneToMany(mappedBy = "compra", cascade = CascadeType.PERSIST)
	private Set<Pagamento> pagamentos = new HashSet<>();

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

	public StatusDaCompra getStatusDaCompra() {
		return statusDaCompra;
	}

	public void addPagamento(Pagamento pagamento) {
		if (pagamento.getStatusDePagamento().equals(StatusDePagamento.SUCESSO))
			this.statusDaCompra = StatusDaCompra.FINALIZADA;

		this.pagamentos.add(pagamento);
	}

}