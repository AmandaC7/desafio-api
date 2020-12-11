package desafio.api.repository.venda;

import java.util.List;

import desafio.api.model.Venda;
import desafio.api.repository.filter.VendaFilter;

public interface VendaRepositoryQuery {

	public List<Venda> filtrar(VendaFilter vendaFilter);

}
