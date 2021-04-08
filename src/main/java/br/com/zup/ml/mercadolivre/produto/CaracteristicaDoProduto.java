package br.com.zup.ml.mercadolivre.produto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class CaracteristicaDoProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotEmpty
	@JsonProperty("nome")
	private String nome;

	@Column(nullable = false)
	@NotEmpty
	@JsonProperty("descricao")
	private String descricao;

	@ManyToOne(optional = false)
	private Produto produto;

	@Deprecated
	public CaracteristicaDoProduto() {
	}

	public CaracteristicaDoProduto(@NotEmpty String nome, @NotEmpty String descricao, Produto produto) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.produto = produto;
	}
	
	public CaracteristicaDoProduto(@NotEmpty String nome, @NotEmpty String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}