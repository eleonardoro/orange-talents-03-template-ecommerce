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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import br.com.zup.ml.mercadolivre.categoria.Categoria;
import br.com.zup.ml.mercadolivre.produto.caracteristica_do_produto.CaracteristicaDoProduto;
import br.com.zup.ml.mercadolivre.produto.caracteristica_do_produto.NovaCaracteristicaDoProdutoRequest;
import br.com.zup.ml.mercadolivre.produto.imagem.Imagem;
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
	private Usuario usuarioDono;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<CaracteristicaDoProduto> caracteristicas = new HashSet<CaracteristicaDoProduto>();

	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private List<Imagem> imagens;

	@Deprecated
	public Produto() {
	}

	public Produto(String nome, BigDecimal valor, Integer quantidade, @Length(min = 1, max = 1000) String descricao,
			Categoria categoria, Usuario usuarioDono,
			@Size(min = 3) @Valid List<NovaCaracteristicaDoProdutoRequest> caracteristicas) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.usuarioDono = usuarioDono;
		this.caracteristicas.addAll(caracteristicas.stream()
				.map(caracteristica -> caracteristica.converterParaCaracteristica(this)).collect(Collectors.toSet()));
	}

	public Long getId() {
		return id;
	}

	public Usuario getUsuarioDono() {
		return usuarioDono;
	}

	public void setImagens(List<MultipartFile> imagensParaSalvar) {
		imagensParaSalvar.forEach(imagem -> imagens.add(new Imagem(imagem.getName(), "urlll", this)));
	}
}