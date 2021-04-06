package br.com.zup.ml.mercadolivre.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.zup.ml.mercadolivre.validacoes.ValorUnico;

public class CriaUsuarioRequest {

	@NotEmpty
	@Email
	@ValorUnico(domainClass = Usuario.class, fieldName = "usuario")
	private String usuario;

	@NotEmpty
	@Size(min = 6)
	private String senha;

	public Usuario converterParaUsuario() {
		return new Usuario(usuario, senha);
	}

	public CriaUsuarioRequest(@NotEmpty @Email String usuario, @NotEmpty @Size(min = 6) String senha) {
		super();
		this.usuario = usuario;
		this.senha = senha;
	}

}