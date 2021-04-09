package br.com.zup.ml.mercadolivre.produto.opiniao;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.ml.mercadolivre.produto.ProdutoRepository;
import br.com.zup.ml.mercadolivre.usuario.Usuario;

@RestController
@RequestMapping("/opinioes")
public class NovaOpiniaoDoProdutoController {
	
	ProdutoRepository produtoRepository;
	OpiniaoDoProdutoRepository opiniaoDoProdutoRepository;

	public NovaOpiniaoDoProdutoController(ProdutoRepository produtoRepository,
			OpiniaoDoProdutoRepository opiniaoDoProdutoRepository) {
		this.produtoRepository = produtoRepository;
		this.opiniaoDoProdutoRepository = opiniaoDoProdutoRepository;
	}

	@PostMapping
	public HttpStatus criaOpiniaoDoProduto(@RequestBody @Valid NovaOpiniaoDoProdutoRequest novaOpiniaoDoProdutoRequest,
			@AuthenticationPrincipal Usuario usuario) {
		OpiniaoDoProduto opiniaoDoProduto = novaOpiniaoDoProdutoRequest.converterParaOpiniaoDoProduto(produtoRepository);
		
		opiniaoDoProdutoRepository.save(opiniaoDoProduto);

		return HttpStatus.OK;
	}
}
