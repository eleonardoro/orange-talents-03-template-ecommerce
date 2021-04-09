package br.com.zup.ml.mercadolivre.produto.pergunta;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import br.com.zup.ml.mercadolivre.produto.Produto;
import br.com.zup.ml.mercadolivre.usuario.Usuario;

@Entity
public class PerguntaDoProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String titulo;
	
	@ManyToOne
	private Produto produto;
	
	@ManyToOne
	private Usuario usuario;
	
	@Column(nullable = false, updatable = false)
	private final LocalDateTime dataCriacao = LocalDateTime.now();

	public PerguntaDoProduto(@NotEmpty String titulo, Produto produto, Usuario usuario) {
		this.titulo = titulo;
		this.produto = produto;
		this.usuario = usuario;
	}
	
	public String getTitulo() {
		return titulo;
	}
}