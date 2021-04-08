
package br.com.zup.ml.mercadolivre.produto;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.ml.mercadolivre.categoria.CategoriaRepository;
import br.com.zup.ml.mercadolivre.usuario.Usuario;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	private CategoriaRepository categoriaRepository;
	private ProdutoRepository produtoRepository;

	public ProdutoController(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository) {
		this.categoriaRepository = categoriaRepository;
		this.produtoRepository = produtoRepository;
	}

	@PostMapping
	public HttpStatus criaProduto(@RequestBody @Valid CriaProdutoRequest criaProdutoRequest,
			@AuthenticationPrincipal Usuario usuarioDono) {
		produtoRepository.save(criaProdutoRequest.converterParaProduto(categoriaRepository, usuarioDono));

		return HttpStatus.OK;
	}
}
