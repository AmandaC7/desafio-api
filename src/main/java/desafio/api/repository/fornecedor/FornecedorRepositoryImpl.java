package desafio.api.repository.fornecedor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.ObjectUtils;

import desafio.api.model.Fornecedor;
import desafio.api.repository.filter.FornecedorFilter;

public class FornecedorRepositoryImpl implements FornecedorRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Fornecedor> filtrar(FornecedorFilter fornecedortFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Fornecedor> criteria = builder.createQuery(Fornecedor.class);
		Root<Fornecedor> root = criteria.from(Fornecedor.class);

		Predicate[] predicates = criarRestricoes(fornecedortFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Fornecedor> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(FornecedorFilter fornecedortFilter, CriteriaBuilder builder,
			Root<Fornecedor> root) {

		List<Predicate> predicates = new ArrayList<>();

		if (!ObjectUtils.isEmpty(fornecedortFilter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get("nome")),
					"%" + fornecedortFilter.getNome().toLowerCase() + "%"));
		}
		
		if (!ObjectUtils.isEmpty(fornecedortFilter.getCnpj())) {
			predicates.add(builder.like(builder.lower(root.get("cnpj")),
					"%" + fornecedortFilter.getCnpj().toLowerCase() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
