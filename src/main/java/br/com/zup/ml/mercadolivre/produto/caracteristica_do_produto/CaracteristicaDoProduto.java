package br.com.zup.ml.mercadolivre.produto.caracteristica_do_produto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.zup.ml.mercadolivre.produto.Produto;

@Entity
public class CaracteristicaDoProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String descricao;

	@ManyToOne
	private Produto produto;

	@Deprecated
	public CaracteristicaDoProduto() {
	}

	public CaracteristicaDoProduto(String nome, String descricao, Produto produto) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	@JsonIgnore
	public Produto getProduto() {
		return produto;
	}
}