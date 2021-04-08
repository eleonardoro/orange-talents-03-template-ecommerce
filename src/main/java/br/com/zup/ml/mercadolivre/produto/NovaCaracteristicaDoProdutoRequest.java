package br.com.zup.ml.mercadolivre.produto;

import javax.validation.constraints.NotEmpty;

public class NovaCaracteristicaDoProdutoRequest {

	@NotEmpty
	private String nome;

	@NotEmpty
	private String descricao;

	public NovaCaracteristicaDoProdutoRequest(@NotEmpty String nome, @NotEmpty String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public CaracteristicaDoProduto converterParaCaracteristica(Produto produto) {
		return new CaracteristicaDoProduto(nome, descricao, produto);
	}
}