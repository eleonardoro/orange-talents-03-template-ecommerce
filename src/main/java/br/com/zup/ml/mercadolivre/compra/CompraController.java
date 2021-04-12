package br.com.zup.ml.mercadolivre.compra;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.ml.mercadolivre.produto.Produto;
import br.com.zup.ml.mercadolivre.produto.ProdutoRepository;
import br.com.zup.ml.mercadolivre.services.EmailService;
import br.com.zup.ml.mercadolivre.usuario.Usuario;

@RestController
@RequestMapping("compras")
public class CompraController {

	private CompraRepository compraRepository;
	private ProdutoRepository produtoRepository;
	private EmailService emailService;

	public CompraController(CompraRepository compraRepository, ProdutoRepository produtoRepository,
			EmailService emailService) {
		this.compraRepository = compraRepository;
		this.produtoRepository = produtoRepository;
		this.emailService = emailService;
	}

	@PostMapping
	public ResponseEntity<Void> criaCompra(@Valid @RequestBody NovaCompraRequest novaCompraRequest,
			@AuthenticationPrincipal Usuario usuarioLogado, HttpServletResponse response)  {

		Compra compra = novaCompraRequest.converterParaCompra(produtoRepository, usuarioLogado);

		Produto produto = produtoRepository.findById(novaCompraRequest.getIdProduto()).get();

		produto.abateQuantidade(novaCompraRequest.getQuantidade());

		emailService.enviaEmailParaDonoDoProdutoParaNovaCompra(produto, compra);

		compraRepository.save(compra);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create(compra.getFormaDePagamento().montaUrlDeEnvio(compra.getId().toString())));
		return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);

	}
}