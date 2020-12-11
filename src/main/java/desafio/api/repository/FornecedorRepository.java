package desafio.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import desafio.api.model.Fornecedor;
import desafio.api.repository.fornecedor.FornecedorRepositoryQuery;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>, FornecedorRepositoryQuery{

}
