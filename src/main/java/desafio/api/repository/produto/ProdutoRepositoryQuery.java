package desafio.api.repository.produto;

import java.util.List;

import desafio.api.model.Produto;
import desafio.api.repository.filter.ProdutoFilter;

public interface ProdutoRepositoryQuery {

	public List<Produto> filtrar(ProdutoFilter produtotFilter);
}
