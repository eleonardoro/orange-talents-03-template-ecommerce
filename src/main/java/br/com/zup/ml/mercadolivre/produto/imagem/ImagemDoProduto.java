package br.com.zup.ml.mercadolivre.produto.imagem;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

import br.com.zup.ml.mercadolivre.produto.Produto;

@Entity
public class ImagemDoProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(nullable = false)
	private String nome;

	@NotEmpty
	@Column(nullable = false, unique = true)
	private String url;

	@Column(nullable = false, updatable = false)
	private final LocalDateTime dataUpload = LocalDateTime.now();

	@NotNull
	@ManyToOne
	private Produto produto;

	@Deprecated
	public ImagemDoProduto() {
	}

	public ImagemDoProduto(@NotEmpty String nome, @NotEmpty String url, @NotEmpty Produto produto) {
		this.nome = nome;
		this.url = url;
		this.produto = produto;
	}
}