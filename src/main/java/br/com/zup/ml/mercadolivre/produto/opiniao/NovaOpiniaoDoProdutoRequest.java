package br.com.zup.ml.mercadolivre.produto.opiniao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zup.ml.mercadolivre.produto.Produto;
import br.com.zup.ml.mercadolivre.produto.ProdutoRepository;
import br.com.zup.ml.mercadolivre.validacoes.ExisteId;

public class NovaOpiniaoDoProdutoRequest {

	@Min(value = 1)
	@Max(value = 5)
	@NotNull
	private Integer nota;
	
	@NotEmpty
	private String titulo;
	
	@NotEmpty
	@Size(max = 500)
	private String descricao;
	
	@NotNull
	@ExisteId(classe = Produto.class, atributo = "id")
	private Long idProduto;

	public NovaOpiniaoDoProdutoRequest(@Min(1) @Max(5) @NotNull Integer nota, @NotEmpty String titulo,
			@NotEmpty @Size(max = 500) String descricao, @NotNull Long idProduto) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.idProduto = idProduto;
	}

	@Override
	public String toString() {
		return "NovaOpiniaoDoProdutoRequest [nota=" + nota + ", titulo=" + titulo + ", descricao=" + descricao
				+ ", idProduto=" + idProduto + "]";
	}

	public OpiniaoDoProduto converterParaOpiniaoDoProduto(ProdutoRepository produtoRepository) {
		return new OpiniaoDoProduto(nota, titulo, descricao, produtoRepository.findById(idProduto).get());
	}
}