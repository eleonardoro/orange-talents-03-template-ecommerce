package br.com.zup.ml.mercadolivre.pagamento.rankingdevendedores;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import br.com.zup.ml.mercadolivre.compra.Compra;
import br.com.zup.ml.mercadolivre.usuario.Usuario;

@Entity
public class RankingDeVendedores {

	@Id
	@Type(type = "uuid-char")
	private final UUID id = UUID.randomUUID();

	@ManyToOne(optional = false)
	private Compra compra;

	@ManyToOne(optional = false)
	private Usuario vendedor;

	@Deprecated
	public RankingDeVendedores() {
	}

	public RankingDeVendedores(Compra compra, Usuario vendedor) {
		this.compra = compra;
		this.vendedor = vendedor;
	}
}