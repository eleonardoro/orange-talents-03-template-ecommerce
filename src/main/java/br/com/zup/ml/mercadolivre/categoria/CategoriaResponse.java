package br.com.zup.ml.mercadolivre.categoria;

public class CategoriaResponse {

	private Long id;
	private String nomeCategoria;
	private Long categoriaMaeId;

	public CategoriaResponse(Categoria categoria) {
		this.id = categoria.getId();
		this.nomeCategoria = categoria.getNomeCategoria();
		this.categoriaMaeId = categoria.getCategoriaMae().getId();
	}

	public Long getId() {
		return id;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public Long getCategoriaMaeId() {
		return categoriaMaeId;
	}
}