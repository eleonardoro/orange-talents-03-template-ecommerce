package br.com.zup.ml.mercadolivre.usuario;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String usuario;

	@Column(nullable = false)
	private String senha;

	@Column(nullable = false, updatable = false)
	private LocalDateTime dataCriacao = LocalDateTime.now();

	@Deprecated
	public Usuario() {
	}

	public Usuario(String usuario, String senha) {
		this.usuario = usuario;
		
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		this.senha = encoder.encode(senha);
	}

	public Long getId() {
		return id;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getSenha() {
		return senha;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

}