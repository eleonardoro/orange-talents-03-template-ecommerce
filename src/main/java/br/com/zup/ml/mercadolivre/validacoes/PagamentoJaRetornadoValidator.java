package br.com.zup.ml.mercadolivre.validacoes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

import br.com.zup.ml.mercadolivre.pagamento.Pagamento;

public class PagamentoJaRetornadoValidator implements ConstraintValidator<PagamentoJaRetornado, String> {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void initialize(PagamentoJaRetornado params) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		Query query = manager.createQuery(
				"select 1 from " + Pagamento.class.getName() + " where id_pagamento_gateway_de_pagamento = :value");
		query.setParameter("value", value);

		List<?> list = query.getResultList();
		Assert.isTrue(list.size() <= 1, "aconteceu algo bizarro e você tem mais de um pagamento com o id: " + value);

		return list.isEmpty();
	}
}