package desafio.api.repository.cliente;

import java.util.List;

import desafio.api.model.Cliente;
import desafio.api.repository.filter.ClienteFilter;

public interface ClienteRepositoryQuery {

	public List<Cliente> filtrar(ClienteFilter clientetFilter);

}
