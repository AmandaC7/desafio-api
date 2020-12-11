package desafio.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import desafio.api.model.Cliente;
import desafio.api.repository.cliente.ClienteRepositoryQuery;

public interface ClienteRepository extends JpaRepository<Cliente, Long>, ClienteRepositoryQuery{

}
