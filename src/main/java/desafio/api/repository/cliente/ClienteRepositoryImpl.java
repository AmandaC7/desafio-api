package desafio.api.repository.cliente;

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

import desafio.api.model.Cliente;
import desafio.api.repository.filter.ClienteFilter;

public class ClienteRepositoryImpl implements ClienteRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;	
	
	@Override
	public List<Cliente> filtrar(ClienteFilter clientetFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteria = builder.createQuery(Cliente.class);
		Root<Cliente> root = criteria.from(Cliente.class);

		Predicate[] predicates = criarRestricoes(clientetFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Cliente> query = manager.createQuery(criteria);
		return query.getResultList();
			}

	private Predicate[] criarRestricoes(ClienteFilter clientetFilter, CriteriaBuilder builder, Root<Cliente> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!ObjectUtils.isEmpty(clientetFilter.getName())) {
			predicates.add(builder.like(builder.lower(root.get("nome")),
					"%" + clientetFilter.getName().toLowerCase() + "%"));
		}
		if (!ObjectUtils.isEmpty(clientetFilter.getDocumento())) {
			predicates.add(builder.like(builder.lower(root.get("nome")),
					"%" + clientetFilter.getDocumento().toLowerCase() + "%"));
		}
		if (!ObjectUtils.isEmpty(clientetFilter.getEmail())) {
			predicates.add(builder.like(builder.lower(root.get("nome")),
					"%" + clientetFilter.getEmail().toLowerCase() + "%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	
}
