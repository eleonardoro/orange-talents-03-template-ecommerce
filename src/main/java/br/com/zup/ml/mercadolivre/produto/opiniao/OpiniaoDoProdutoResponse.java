package br.com.zup.ml.mercadolivre.produto.opiniao;

public class OpiniaoDoProdutoResponse {

	private Long id;
	private Integer nota;
	private String titulo;
	private String descricao;

	public OpiniaoDoProdutoResponse(OpiniaoDoProduto opiniaoDoProduto) {
		this.id = opiniaoDoProduto.getId();
		this.nota = opiniaoDoProduto.getNota();
		this.titulo = opiniaoDoProduto.getTitulo();
		this.descricao = opiniaoDoProduto.getDescricao();
	}

	public Long getId() {
		return id;
	}

	public Integer getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}
}