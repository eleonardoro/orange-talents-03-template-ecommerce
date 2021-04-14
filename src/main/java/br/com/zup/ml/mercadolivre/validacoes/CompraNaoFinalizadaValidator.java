package br.com.zup.ml.mercadolivre.validacoes;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.zup.ml.mercadolivre.compra.Compra;
import br.com.zup.ml.mercadolivre.compra.StatusDaCompra;

public class CompraNaoFinalizadaValidator implements ConstraintValidator<CompraNaoFinalizada, UUID> {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void initialize(CompraNaoFinalizada params) {
	}

	@Override
	public boolean isValid(UUID value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		Query query = manager.createQuery("select c from " + Compra.class.getName() + " c where id = :value");
		query.setParameter("value", value);

		Compra compra = (Compra) query.getSingleResult();

		return !compra.getStatusDaCompra().equals(StatusDaCompra.FINALIZADA);
	}
}