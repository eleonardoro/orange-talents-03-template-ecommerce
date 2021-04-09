package br.com.zup.ml.mercadolivre.produto.imagem;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.ml.mercadolivre.produto.Produto;
import br.com.zup.ml.mercadolivre.produto.ProdutoRepository;
import br.com.zup.ml.mercadolivre.usuario.Usuario;

@RestController
@RequestMapping("/imagens")
public class UploadImagemController {

	private ProdutoRepository produtoRepository;

	public UploadImagemController(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	@PostMapping(value = "/adicionar/{id}")
	@Transactional
	public HttpStatus handleFileUpload(@PathVariable("id") Long idProduto,
			@Valid NovaImagemDoProdutoRequest novaImagemDoProdutoRequest, @AuthenticationPrincipal Usuario usuario) {
		
		System.out.println(idProduto);

		Optional<Produto> produto = produtoRepository.findById(idProduto);

		if (!produto.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado");
		
		Produto p = produto.get();

		if (!p.isUsuarioDono(usuario))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Um produto so pode ser alterado por seu dono");

		p.setImagens(novaImagemDoProdutoRequest);

		return HttpStatus.OK;
	}

}
