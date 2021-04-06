package br.com.zup.ml.mercadolivre.usuario;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private UsuarioRepository usuarioRepository;

	public UsuarioController(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@PostMapping
	public HttpStatus criaUsuario(@RequestBody @Valid CriaUsuarioRequest criaUsuarioRequest) {
		Usuario usuario = criaUsuarioRequest.converterParaUsuario();
		usuarioRepository.save(usuario);

		return HttpStatus.OK;
	}
}
