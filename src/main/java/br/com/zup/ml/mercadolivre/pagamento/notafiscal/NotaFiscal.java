package br.com.zup.ml.mercadolivre.pagamento.notafiscal;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import br.com.zup.ml.mercadolivre.compra.Compra;
import br.com.zup.ml.mercadolivre.usuario.Usuario;

@Entity
public class NotaFiscal {

	@Id
	@Type(type = "uuid-char")
	private final UUID id = UUID.randomUUID();

	@ManyToOne(optional = false)
	private Compra compra;

	@ManyToOne(optional = false)
	private Usuario comprador;

	@Deprecated
	public NotaFiscal() {
	}

	public NotaFiscal(Compra compra, Usuario comprador) {
		this.compra = compra;
		this.comprador = comprador;
	}
}