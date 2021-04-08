package br.com.zup.ml.mercadolivre.produto;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import br.com.zup.ml.mercadolivre.categoria.Categoria;
import br.com.zup.ml.mercadolivre.categoria.CategoriaRepository;
import br.com.zup.ml.mercadolivre.validacoes.ExistsId;
import br.com.zup.ml.mercadolivre.validacoes.ValorUnico;

public class CriaProdutoRequest {

	@NotEmpty
	@ValorUnico(domainClass = Produto.class, fieldName = "nome")
	private String nome;

	@NotNull
	@Positive
	private BigDecimal valor;

	@NotNull
	@PositiveOrZero
	private Integer quantidade;

	@NotEmpty
	@Length(min = 1, max = 1000)
	private String descricao;

	@NotNull
	@Positive
	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private Long idCategoria;

	public CriaProdutoRequest(@NotEmpty String nome, @NotNull @Positive BigDecimal valor,
			@NotNull @PositiveOrZero Integer quantidade, @NotEmpty @Length(min = 1, max = 1000) String descricao,
			@NotNull @Positive Long idCategoria) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.idCategoria = idCategoria;
	}

	public Produto converterParaProduto(CategoriaRepository categoriaRepository) {
		Categoria categoria = categoriaRepository.findById(idCategoria).get();

		return new Produto(nome, valor, quantidade, descricao, categoria);
	}
}