package br.com.zup.ml.mercadolivre.categoria;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String nomeCategoria;

	@ManyToOne(optional = true)
	private Categoria categoriaMae;

	@Deprecated
	public Categoria() {
	}

	public Categoria(String nomeCategoria) {
		super();
		this.nomeCategoria = nomeCategoria;
	}

	public void setCategoriaMae(Categoria categoriaMae) {
		this.categoriaMae = categoriaMae;
	}

}