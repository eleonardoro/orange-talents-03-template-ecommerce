package br.com.zup.ml.mercadolivre.validacoes;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = { TemQuantidadeDisponivelValidator.class })
@Target({ TYPE })
@Retention(RUNTIME)
public @interface TemQuantidadeDisponivel {

	String message() default "aaaaaaaaaaaaaaa";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}