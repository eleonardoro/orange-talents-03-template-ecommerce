package br.com.zup.ml.mercadolivre.validacoes;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.zup.ml.mercadolivre.compra.FormaDePagamento;

public class ExisteFormaDePagamentoValidator implements ConstraintValidator<ExisteFormaDePagamento, String> {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void initialize(ExisteFormaDePagamento params) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		boolean existe = false;
		for (FormaDePagamento fp : FormaDePagamento.values()) {
			if (fp.name().equalsIgnoreCase(value))
				existe = true;
		}

		return existe;
	}
}