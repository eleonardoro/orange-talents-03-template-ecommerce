package br.com.zup.ml.mercadolivre.produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;

import br.com.zup.ml.mercadolivre.categoria.Categoria;
import br.com.zup.ml.mercadolivre.compra.Compra;
import br.com.zup.ml.mercadolivre.produto.caracteristica_do_produto.CaracteristicaDoProduto;
import br.com.zup.ml.mercadolivre.produto.caracteristica_do_produto.NovaCaracteristicaDoProdutoRequest;
import br.com.zup.ml.mercadolivre.produto.imagem.ImagemDoProduto;
import br.com.zup.ml.mercadolivre.produto.imagem.NovaImagemDoProdutoRequest;
import br.com.zup.ml.mercadolivre.produto.opiniao.OpiniaoDoProduto;
import br.com.zup.ml.mercadolivre.produto.pergunta.PerguntaDoProduto;
import br.com.zup.ml.mercadolivre.usuario.Usuario;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private BigDecimal valor;

	@Column(nullable = false)
	private Integer quantidade;

	@Column(nullable = false)
	@Length(min = 1, max = 1000)
	private String descricao;

	@ManyToOne(optional = false)
	private Categoria categoria;

	@Column(nullable = false, updatable = false)
	private final LocalDateTime dataCriacao = LocalDateTime.now();

	@ManyToOne(optional = false)
	private Usuario vendedor;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<CaracteristicaDoProduto> caracteristicas = new HashSet<>();

	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<ImagemDoProduto> imagens = new HashSet<>();

	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<OpiniaoDoProduto> opinioes = new HashSet<>();

	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<PerguntaDoProduto> perguntas = new HashSet<>();

	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	Set<Compra> compras = new HashSet<>();

	@Deprecated
	public Produto() {
	}

	public Produto(String nome, BigDecimal valor, Integer quantidade, String descricao, Categoria categoria,
			Usuario usuarioDono, List<NovaCaracteristicaDoProdutoRequest> caracteristicas) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.vendedor = usuarioDono;
		this.caracteristicas.addAll(caracteristicas.stream()
				.map(caracteristica -> caracteristica.converterParaCaracteristica(this)).collect(Collectors.toSet()));
	}

	public void setImagens(@Valid NovaImagemDoProdutoRequest imagensParaSalvar) {
		imagensParaSalvar.getImagens().forEach(imagem -> {
			this.imagens.add(new ImagemDoProduto(imagem.getOriginalFilename(), LocalDateTime.now().toString(), this));
		});
	}

	public boolean isUsuarioDono(Usuario usuario) {
		return usuario.equals(this.vendedor);
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Usuario getVendedor() {
		return vendedor;
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

	public Usuario getUsuarioDono() {
		return vendedor;
	}

	public Set<CaracteristicaDoProduto> getCaracteristicas() {
		return caracteristicas;
	}

	public Set<ImagemDoProduto> getImagens() {
		return imagens;
	}

	public Set<OpiniaoDoProduto> getOpinioes() {
		return opinioes;
	}

	public Set<PerguntaDoProduto> getPerguntas() {
		return perguntas;
	}

	public boolean abateQuantidade(Integer quantidadeVenda) {
		boolean retorno = false;

		if (quantidade > 0 && quantidade >= quantidadeVenda) {
			retorno = true;
			this.quantidade -= quantidadeVenda;
		}

		return retorno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}