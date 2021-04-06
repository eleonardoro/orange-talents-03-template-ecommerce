package br.com.zup.ml.mercadolivre.categoria;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	private CategoriaRepository categoriaRepository;

	public CategoriaController(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	@PostMapping
	public HttpStatus criaCategoria(@RequestBody @Valid CriaCategoriaRequest criaCategoriaRequest) {
		Categoria categoria = criaCategoriaRequest.converterParaCategoria(categoriaRepository);
		categoriaRepository.save(categoria);

		return HttpStatus.OK;
	}
}
