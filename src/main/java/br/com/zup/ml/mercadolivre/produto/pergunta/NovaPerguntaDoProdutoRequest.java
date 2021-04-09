package br.com.zup.ml.mercadolivre.produto.pergunta;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.zup.ml.mercadolivre.produto.Produto;
import br.com.zup.ml.mercadolivre.produto.ProdutoRepository;
import br.com.zup.ml.mercadolivre.usuario.Usuario;
import br.com.zup.ml.mercadolivre.validacoes.ExisteId;

public class NovaPerguntaDoProdutoRequest {

	@NotEmpty
	private String titulo;
	
	@NotNull
	@ExisteId(classe = Produto.class, atributo = "id")
	private Long idProduto;

	public NovaPerguntaDoProdutoRequest(@NotEmpty String titulo, @NotNull Long idProduto) {
		this.titulo = titulo;
		this.idProduto = idProduto;
	}

	@Override
	public String toString() {
		return "NovaPerguntaDoProdutoRequest [titulo=" + titulo + ", idProduto=" + idProduto + "]";
	}

	public PerguntaDoProduto converterParaPerguntaDoProduto(ProdutoRepository produtoRepository, Usuario usuario) {
		return new PerguntaDoProduto(titulo, produtoRepository.findById(idProduto).get(), usuario);
	}
	
	public Long getIdProduto() {
		return idProduto;
	}
}