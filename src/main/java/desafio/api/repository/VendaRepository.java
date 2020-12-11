package desafio.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import desafio.api.model.Venda;
import desafio.api.repository.venda.VendaRepositoryQuery;

public interface VendaRepository extends JpaRepository<Venda, Long>, VendaRepositoryQuery{

}
