package br.com.zup.ml.mercadolivre.produto.pergunta;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.ml.mercadolivre.produto.ProdutoRepository;
import br.com.zup.ml.mercadolivre.services.EmailService;
import br.com.zup.ml.mercadolivre.usuario.Usuario;

@RestController
@RequestMapping("/perguntas")
public class NovaPerguntaDoProdutoController {

	
	private ProdutoRepository produtoRepository;
	private PerguntaRepository perguntaRepository;
	
	public NovaPerguntaDoProdutoController(ProdutoRepository produtoRepository, PerguntaRepository perguntaRepository) {
		super();
		this.produtoRepository = produtoRepository;
		this.perguntaRepository = perguntaRepository;
	}

	@PostMapping
	public HttpStatus criaPerguntaDoProduto(@RequestBody @Valid NovaPerguntaDoProdutoRequest novaPerguntaDoProdutoRequest,
			@AuthenticationPrincipal Usuario usuario) {
		
		PerguntaDoProduto perguntaDoProduto = novaPerguntaDoProdutoRequest.converterParaPerguntaDoProduto(produtoRepository, usuario);
		
		PerguntaDoProduto perguntaSalva =  perguntaRepository.save(perguntaDoProduto);
		
		EmailService.enviaEmailParaDonoDoProdutoParaNovaPergunta(produtoRepository.findById(novaPerguntaDoProdutoRequest.getIdProduto()).get(), usuario, perguntaSalva);
		
		return HttpStatus.OK;
	}
}
