package br.com.zup.ml.mercadolivre.produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
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
import org.springframework.util.Assert;

import br.com.zup.ml.mercadolivre.categoria.Categoria;

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

	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<CaracteristicaDoProduto> caracteristicas = new HashSet<>();

	@Deprecated
	public Produto() {
	}

	public Produto(String nome, BigDecimal valor, Integer quantidade, @Length(min = 1, max = 1000) String descricao,
			Categoria categoria, @Size(min = 3) @Valid Collection<CaracteristicaDoProdutoRequest> caracteristicas) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;

		this.caracteristicas.addAll(caracteristicas.stream()
				.map(caracteristica -> caracteristica.converterParaCaracteristicaDoProduto(this))
				.collect(Collectors.toSet()));

		// 1
		Assert.isTrue(this.caracteristicas.size() >= 3, "Todo produto precisa ter no mínimo 3 ou mais características");
	}

	public Long getId() {
		return id;
	}

}