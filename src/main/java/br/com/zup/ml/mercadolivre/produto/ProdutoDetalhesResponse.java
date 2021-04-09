package br.com.zup.ml.mercadolivre.produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.zup.ml.mercadolivre.categoria.Categoria;
import br.com.zup.ml.mercadolivre.produto.caracteristica_do_produto.CaracteristicaDoProduto;
import br.com.zup.ml.mercadolivre.produto.caracteristica_do_produto.CaracteristicaDoProdutoResponse;
import br.com.zup.ml.mercadolivre.produto.imagem.ImagemDoProduto;
import br.com.zup.ml.mercadolivre.produto.imagem.ImagemDoProdutoResponse;
import br.com.zup.ml.mercadolivre.produto.opiniao.OpiniaoDoProduto;
import br.com.zup.ml.mercadolivre.produto.opiniao.OpiniaoDoProdutoResponse;
import br.com.zup.ml.mercadolivre.produto.pergunta.PerguntaDoProduto;
import br.com.zup.ml.mercadolivre.produto.pergunta.PerguntaDoProdutoResponse;
import br.com.zup.ml.mercadolivre.usuario.Usuario;
import br.com.zup.ml.mercadolivre.usuario.UsuarioConcisoResponse;

public class ProdutoDetalhesResponse {

	private Long id;
	private String nome;
	private BigDecimal valor;
	private Integer quantidade;
	private String descricao;
	private Categoria categoria;
	private final LocalDateTime dataCriacao = LocalDateTime.now();
	private Usuario usuarioDono;
	private Set<CaracteristicaDoProduto> caracteristicas = new HashSet<>();
	private Set<ImagemDoProduto> imagens = new HashSet<>();
	private Set<OpiniaoDoProduto> opinioes = new HashSet<>();
	private Set<PerguntaDoProduto> perguntas = new HashSet<>();

	public ProdutoDetalhesResponse(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.quantidade = produto.getQuantidade();
		this.descricao = produto.getDescricao();
		this.categoria = produto.getCategoria();
		this.usuarioDono = produto.getUsuarioDono();
		this.caracteristicas = produto.getCaracteristicas();
		this.imagens = produto.getImagens();
		this.opinioes = produto.getOpinioes();
		this.perguntas = produto.getPerguntas();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public UsuarioConcisoResponse getUsuarioDono() {
		return new UsuarioConcisoResponse(usuarioDono);
	}

	public Set<CaracteristicaDoProdutoResponse> getCaracteristicas() {
		return this.caracteristicas.stream().map(caracteristica -> new CaracteristicaDoProdutoResponse(caracteristica))
				.collect(Collectors.toSet());
	}

	public Set<ImagemDoProdutoResponse> getImagens() {
		return this.imagens.stream().map(imagem -> new ImagemDoProdutoResponse(imagem)).collect(Collectors.toSet());
	}

	public Set<OpiniaoDoProdutoResponse> getOpinioes() {
		return this.opinioes.stream().map(opiniao -> new OpiniaoDoProdutoResponse(opiniao)).collect(Collectors.toSet());
	}

	public Set<PerguntaDoProdutoResponse> getPerguntas() {
		return this.perguntas.stream().map(pergunta -> new PerguntaDoProdutoResponse(pergunta))
				.collect(Collectors.toSet());
	}

	public Double getMediaNotas() {
		return this.opinioes.stream().collect(Collectors.averagingDouble(OpiniaoDoProduto::getNota));
	}

	public Integer getQuantidadeNotas() {
		return opinioes.size();
	}
}