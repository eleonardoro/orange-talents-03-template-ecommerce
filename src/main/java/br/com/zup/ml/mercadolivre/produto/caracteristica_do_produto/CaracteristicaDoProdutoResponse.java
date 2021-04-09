package br.com.zup.ml.mercadolivre.produto.caracteristica_do_produto;

public class CaracteristicaDoProdutoResponse {

	private Long id;
	private String nome;
	private String descricao;

	public CaracteristicaDoProdutoResponse(CaracteristicaDoProduto caracteristicaDoProduto) {
		this.id = caracteristicaDoProduto.getId();
		this.nome = caracteristicaDoProduto.getNome();
		this.descricao = caracteristicaDoProduto.getDescricao();
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
}