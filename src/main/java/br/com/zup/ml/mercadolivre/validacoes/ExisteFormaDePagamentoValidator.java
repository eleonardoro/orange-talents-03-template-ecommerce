package br.com.zup.ml.mercadolivre.validacoes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.zup.ml.mercadolivre.compra.FormaDePagamento;

public class ExisteFormaDePagamentoValidator implements ConstraintValidator<ExisteFormaDePagamento, String> {

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