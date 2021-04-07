package br.com.zup.ml.mercadolivre.usuario;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	Optional<Usuario> findByEmail(String email);
}