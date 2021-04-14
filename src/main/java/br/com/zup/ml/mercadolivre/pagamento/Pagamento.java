package br.com.zup.ml.mercadolivre.pagamento;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import br.com.zup.ml.mercadolivre.compra.Compra;

@Entity
public class Pagamento {

	@Id
	@Type(type = "uuid-char")
	private final UUID id = UUID.randomUUID();

	@ManyToOne(optional = false)
	private Compra compra;

	@Column(nullable = false)
	private String idPagamentoGatewayDePagamento;

	@Enumerated(EnumType.STRING)
	private GatewayDePagamento gatewayDePagamento;

	@Enumerated(EnumType.STRING)
	private StatusDePagamento statusDePagamento;

	@Column(nullable = false, updatable = false)
	private final LocalDateTime dataCriacao = LocalDateTime.now();

	@Deprecated
	public Pagamento() {
		super();
	}

	public Pagamento(Compra compra, String idPagamentoGatewayDePagamento, GatewayDePagamento gatewayDePagamento,
			StatusDePagamento statusDePagamento) {
		this.compra = compra;
		this.idPagamentoGatewayDePagamento = idPagamentoGatewayDePagamento;
		this.gatewayDePagamento = gatewayDePagamento;
		this.statusDePagamento = statusDePagamento;
	}

	public StatusDePagamento getStatusDePagamento() {
		return statusDePagamento;
	}

	public UUID getId() {
		return id;
	}

	public GatewayDePagamento getGatewayDePagamento() {
		return gatewayDePagamento;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

}