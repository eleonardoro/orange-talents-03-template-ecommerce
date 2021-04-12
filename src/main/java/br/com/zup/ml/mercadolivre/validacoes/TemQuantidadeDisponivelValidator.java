package br.com.zup.ml.mercadolivre.validacoes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.zup.ml.mercadolivre.compra.NovaCompraRequest;
import br.com.zup.ml.mercadolivre.produto.Produto;

public class TemQuantidadeDisponivelValidator
		implements ConstraintValidator<TemQuantidadeDisponivel, NovaCompraRequest> {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void initialize(TemQuantidadeDisponivel params) {
	}

	@Override
	public boolean isValid(NovaCompraRequest novaCompraRequest, ConstraintValidatorContext context) {
		if (novaCompraRequest == null || novaCompraRequest.getIdProduto() == null
				|| novaCompraRequest.getQuantidade() == null) {
			return true;
		}

		Query query = manager.createQuery("select 1 from " + Produto.class.getName() + " where id = :value");
		query.setParameter("value", novaCompraRequest.getIdProduto());

		List<?> list = query.getResultList();

		if (!list.isEmpty()) {

			Query query2 = manager
					.createQuery("select quantidade from " + Produto.class.getName() + " where id = :value");
			query2.setParameter("value", novaCompraRequest.getIdProduto());

			Integer quantidade = (Integer) query2.getSingleResult();

			return quantidade > 0 && quantidade >= novaCompraRequest.getQuantidade();
		}

		return false;
	}
}