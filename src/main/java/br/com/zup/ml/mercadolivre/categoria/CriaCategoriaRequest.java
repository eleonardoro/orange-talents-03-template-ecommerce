package br.com.zup.ml.mercadolivre.categoria;

import javax.validation.constraints.NotEmpty;

import br.com.zup.ml.mercadolivre.validacoes.ExistsId;
import br.com.zup.ml.mercadolivre.validacoes.ValorUnico;

public class CriaCategoriaRequest {

	@NotEmpty
	@ValorUnico(domainClass = Categoria.class, fieldName = "nomeCategoria")
	private String nomeCategoria;

	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private Long idCategoriaMae;

	public Categoria converterParaCategoria(CategoriaRepository categoriaRepository) {

		Categoria categoria = new Categoria(nomeCategoria);

		if (idCategoriaMae != null) {
			Categoria categoriaMae = categoriaRepository.findById(idCategoriaMae).get();
			categoria.setCategoriaMae(categoriaMae);
		}

		return categoria;

	}

	public CriaCategoriaRequest(@NotEmpty String nomeCategoria, Long idCategoriaMae) {
		super();
		this.nomeCategoria = nomeCategoria;
		this.idCategoriaMae = idCategoriaMae;
	}

}