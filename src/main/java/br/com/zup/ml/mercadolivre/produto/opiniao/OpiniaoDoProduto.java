package br.com.zup.ml.mercadolivre.produto.opiniao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.zup.ml.mercadolivre.produto.Produto;

@Entity
public class OpiniaoDoProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Integer nota;

	@Column(nullable = false)
	private String titulo;

	@Column(nullable = false)
	private String descricao;

	@ManyToOne()
	private Produto produto;

	public OpiniaoDoProduto(Integer nota, String titulo, String descricao, Produto produto) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.produto = produto;
	}

}
