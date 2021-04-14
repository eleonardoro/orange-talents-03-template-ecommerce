package br.com.zup.ml.mercadolivre.validacoes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.zup.ml.mercadolivre.pagamento.StatusDePagamento;

public class StatusPagseguroValidoValidator implements ConstraintValidator<StatusPagseguroValido, String> {

	@Override
	public void initialize(StatusPagseguroValido params) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		boolean existe = false;
		for (StatusDePagamento fp : StatusDePagamento.values()) {
			if (fp.name().equalsIgnoreCase(value))
				existe = true;
		}

		return existe;
	}
}