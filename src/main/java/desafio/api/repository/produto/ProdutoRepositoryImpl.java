package desafio.api.repository.produto;

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

import desafio.api.model.Produto;
import desafio.api.repository.filter.ProdutoFilter;

public class ProdutoRepositoryImpl implements ProdutoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Produto> filtrar(ProdutoFilter produtotFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Produto> criteria = builder.createQuery(Produto.class);
		Root<Produto> root = criteria.from(Produto.class);
		
		Predicate[] predicates = criarRestricoes(produtotFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Produto> query = manager.createQuery(criteria);		
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(ProdutoFilter produtotFilter, CriteriaBuilder builder, Root<Produto> root) {

		List<Predicate> predicates = new ArrayList<>();
		
		if(!ObjectUtils.isEmpty(produtotFilter.getName())) {
			predicates.add(builder.like(builder.lower(root.get("nome")), 
					"%" + produtotFilter.getName().toLowerCase() + "%"));
			
		}
		
		if(produtotFilter.getCategoria() != null) {
			predicates.add(builder.like(builder.lower(root.get("categoria")), 
					"%" + produtotFilter.getCategoria().toLowerCase() + "%"));
		}
		
		if(produtotFilter.getCodigoProduto() != null) {
			predicates.add(builder.like(builder.lower(root.get("codigoProduto")), 
					"%" + produtotFilter.getCodigoProduto().toLowerCase() + "%"));
		}
				
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
