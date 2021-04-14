package br.com.zup.ml.mercadolivre.pagamento.notafiscal;

import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.ml.mercadolivre.compra.Compra;
import br.com.zup.ml.mercadolivre.compra.CompraRepository;
import br.com.zup.ml.mercadolivre.usuario.Usuario;
import br.com.zup.ml.mercadolivre.usuario.UsuarioRepository;
import br.com.zup.ml.mercadolivre.validacoes.ExisteId;

public class CriaNotaFiscalRequest {

	@NotNull
	@ExisteId(classe = Compra.class, atributo = "id")
	private UUID idCompra;

	@NotNull
	@Positive
	@ExisteId(classe = Usuario.class, atributo = "id")
	private Long idComprador;

	public void setIdCompra(UUID idCompra) {
		this.idCompra = idCompra;
	}

	public void setIdComprador(Long idComprador) {
		this.idComprador = idComprador;
	}

	public NotaFiscal converterParaNotaFiscal(CompraRepository compraRepository, UsuarioRepository usuarioRepository) {
		Compra compra = compraRepository.findById(idCompra).get();
		Usuario usuario = usuarioRepository.findById(idComprador).get();

		return new NotaFiscal(compra, usuario);
	}
}