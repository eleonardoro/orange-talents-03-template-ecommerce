package br.com.zup.ml.mercadolivre.usuario;

public class UsuarioConcisoResponse {

	private String email;

	public UsuarioConcisoResponse(Usuario usuario) {
		this.email = usuario.getEmail();
	}

	public String getEmail() {
		return email;
	}
}