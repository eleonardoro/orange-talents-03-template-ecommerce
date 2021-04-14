package br.com.zup.ml.mercadolivre.compra;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface CompraRepository extends CrudRepository<Compra, UUID> {
}