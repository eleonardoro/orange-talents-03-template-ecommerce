package br.com.zup.ml.mercadolivre.produto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CaracteristicaDoProdutoRequest {
	@NotEmpty
	private String nome;

	@NotEmpty
	private String descricao;

	public CaracteristicaDoProdutoRequest(@NotBlank String nome, @NotBlank String descricao) {
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

	public CaracteristicaDoProduto converterParaCaracteristicaDoProduto(@NotNull @Valid Produto produto) {
		return new CaracteristicaDoProduto(nome, descricao, produto);
	}
}