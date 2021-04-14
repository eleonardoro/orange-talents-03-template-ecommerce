package br.com.zup.ml.mercadolivre.pagamento.rankingdevendedores;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.ml.mercadolivre.compra.CompraRepository;
import br.com.zup.ml.mercadolivre.usuario.UsuarioRepository;

@RestController
@RequestMapping("ranking-de-vendedores")
public class RankingDeVendedoresController {

	RankingDeVendedoresRepository notaFiscalRepository;
	private CompraRepository compraRepository;
	private UsuarioRepository usuarioRepository;

	public RankingDeVendedoresController(RankingDeVendedoresRepository notaFiscalRepository, CompraRepository compraRepository,
			UsuarioRepository usuarioRepository) {
		this.notaFiscalRepository = notaFiscalRepository;
		this.compraRepository = compraRepository;
		this.usuarioRepository = usuarioRepository;
	}

	@PostMapping
	public void criaNotaFiscal(@Valid @RequestBody CriaRankingDeVendedoresRequest criaNotaFiscalRequest) {
		notaFiscalRepository.save(criaNotaFiscalRequest.converterParaRankingDeVendedores(compraRepository, usuarioRepository));
	}
}
