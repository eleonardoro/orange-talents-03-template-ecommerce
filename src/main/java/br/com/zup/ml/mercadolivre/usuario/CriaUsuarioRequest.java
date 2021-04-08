package br.com.zup.ml.mercadolivre.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.zup.ml.mercadolivre.validacoes.ValorUnico;

public class CriaUsuarioRequest {

	@NotEmpty
	@Email
	@ValorUnico(classe = Usuario.class, atributo = "email")
	private String email;

	@NotEmpty
	@Size(min = 6)
	private String senha;

	public Usuario converterParaUsuario() {
		return new Usuario(email, senha);
	}

	public CriaUsuarioRequest(@NotEmpty @Email String email, @NotEmpty @Size(min = 6) String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

}