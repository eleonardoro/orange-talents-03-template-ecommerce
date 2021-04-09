package br.com.zup.ml.mercadolivre.produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import br.com.zup.ml.mercadolivre.categoria.Categoria;
import br.com.zup.ml.mercadolivre.categoria.CategoriaRepository;
import br.com.zup.ml.mercadolivre.produto.caracteristica_do_produto.NovaCaracteristicaDoProdutoRequest;
import br.com.zup.ml.mercadolivre.usuario.Usuario;
import br.com.zup.ml.mercadolivre.validacoes.ExisteId;
import br.com.zup.ml.mercadolivre.validacoes.ValorUnico;

public class CriaProdutoRequest {

	@NotEmpty
	@ValorUnico(classe = Produto.class, atributo = "nome")
	private String nome;

	@NotEmpty
	@Length(min = 1, max = 1000)
	private String descricao;

	@NotNull
	@Positive
	private BigDecimal valor;

	@NotNull
	@PositiveOrZero
	private Integer quantidade;

	@NotNull
	@Positive
	@ExisteId(classe = Categoria.class, atributo = "id")
	private Long idCategoria;

	@Size(min = 3)
	@Valid
	private List<NovaCaracteristicaDoProdutoRequest> caracteristicas = new ArrayList<>();

	public CriaProdutoRequest(@NotEmpty String nome, @NotNull @Positive BigDecimal valor,
			@NotNull @PositiveOrZero Integer quantidade, @NotEmpty @Length(min = 1, max = 1000) String descricao,
			@NotNull @Positive Long idCategoria,
			@Size(min = 3) @Valid List<NovaCaracteristicaDoProdutoRequest> caracteristicas) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.idCategoria = idCategoria;
		this.caracteristicas.addAll(caracteristicas);
	}

	public List<NovaCaracteristicaDoProdutoRequest> getCaracteristicas() {
		return caracteristicas;
	}

	public Produto converterParaProduto(CategoriaRepository categoriaRepository, Usuario usuarioDono) {
		Categoria categoria = categoriaRepository.findById(idCategoria).get();

		return new Produto(nome, valor, quantidade, descricao, categoria, usuarioDono, caracteristicas);
	}
}