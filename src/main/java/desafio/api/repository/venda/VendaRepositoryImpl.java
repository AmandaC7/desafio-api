package desafio.api.repository.venda;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import desafio.api.model.Venda;
import desafio.api.repository.filter.VendaFilter;

public class VendaRepositoryImpl implements VendaRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Venda> filtrar(VendaFilter vendaFilter) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Venda> criteria = builder.createQuery(Venda.class);
		Root<Venda> root = criteria.from(Venda.class);

		Predicate[] predicates = criarRestricoes(vendaFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Venda> query = manager.createQuery(criteria);
	
		return query.getResultList();	
	}

	private Predicate[] criarRestricoes(VendaFilter vendaFilter, CriteriaBuilder builder, Root<Venda> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (vendaFilter.getDataCompraDe() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get("dataCompra"), vendaFilter.getDataCompraDe()));
		}

		if (vendaFilter.getDataCompraAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get("dataCompra"), vendaFilter.getDataCompraAte()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	
}
