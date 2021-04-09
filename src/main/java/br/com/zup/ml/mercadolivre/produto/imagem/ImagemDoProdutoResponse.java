package br.com.zup.ml.mercadolivre.produto.imagem;

import java.time.LocalDateTime;

public class ImagemDoProdutoResponse {

	private Long id;
	private String nome;
	private String url;
	private LocalDateTime dataUpload;

	public ImagemDoProdutoResponse(ImagemDoProduto imagemDoProduto) {
		this.id = imagemDoProduto.getId();
		this.nome = imagemDoProduto.getNome();
		this.url = imagemDoProduto.getUrl();
		this.dataUpload = imagemDoProduto.getDataUpload();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getUrl() {
		return url;
	}

	public LocalDateTime getDataUpload() {
		return dataUpload;
	}
}