package desafio.api.repository.fornecedor;

import java.util.List;

import desafio.api.model.Fornecedor;
import desafio.api.repository.filter.FornecedorFilter;

public interface FornecedorRepositoryQuery {

	public List<Fornecedor> filtrar(FornecedorFilter fornecedortFilter);

}
