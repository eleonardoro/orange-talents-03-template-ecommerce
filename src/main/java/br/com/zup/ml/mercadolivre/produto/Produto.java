package br.com.zup.ml.mercadolivre.produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Length;

import br.com.zup.ml.mercadolivre.categoria.Categoria;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private BigDecimal valor;

	@Column(nullable = false)
	private Integer quantidade;

	@Column(nullable = false)
	@Length(min = 1, max = 1000)
	private String descricao;

	@ManyToOne(optional = false)
	private Categoria categoria;

	@Column(nullable = false, updatable = false)
	private final LocalDateTime dataCriacao = LocalDateTime.now();

	@Deprecated
	public Produto() {
	}

	public Produto(String nome, BigDecimal valor, Integer quantidade, @Length(min = 1, max = 1000) String descricao,
			Categoria categoria) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

}