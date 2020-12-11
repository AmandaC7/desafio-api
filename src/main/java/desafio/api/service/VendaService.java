package desafio.api.service;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import desafio.api.model.Produto;
import desafio.api.model.Venda;
import desafio.api.repository.ProdutoRepository;
import desafio.api.repository.VendaRepository;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	public Venda salvar(@Valid Venda venda) {

		double total = 0;
		for (Produto produto : venda.getProdutos()) {
			produto = produtoRepository.findById(produto.getId()).orElse(null);
			if (produto.getPromocao() == true) {	
				total = total + produto.getValorPromocao().doubleValue() 
						- produto.getValor().doubleValue();
			}
			total = total + produto.getValor().doubleValue();
		}
		venda.setTotal_compra(BigDecimal.valueOf(total));

		return vendaRepository.save(venda);

	}

}
