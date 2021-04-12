package br.com.zup.ml.mercadolivre.validacoes;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = { ExisteFormaDePagamentoValidator.class })
@Target({ FIELD })
@Retention(RUNTIME)
public @interface ExisteFormaDePagamento {

	String message() default "{br.com.zup.beanvalidation.existeformadepagamento}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}