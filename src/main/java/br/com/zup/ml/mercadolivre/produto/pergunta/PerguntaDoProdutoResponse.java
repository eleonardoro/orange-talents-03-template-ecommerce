package br.com.zup.ml.mercadolivre.produto.pergunta;

import java.time.LocalDateTime;

import br.com.zup.ml.mercadolivre.usuario.Usuario;

public class PerguntaDoProdutoResponse {

	private Long id;
	private String titulo;
	private Usuario usuario;
	private LocalDateTime dataCriacao;

	public PerguntaDoProdutoResponse(PerguntaDoProduto perguntaDoProduto) {
		this.id = perguntaDoProduto.getId();
		this.titulo = perguntaDoProduto.getTitulo();
		this.usuario = perguntaDoProduto.getUsuario();
		this.dataCriacao = perguntaDoProduto.getDataCriacao();
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
}