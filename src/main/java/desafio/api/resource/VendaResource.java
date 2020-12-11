package desafio.api.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import desafio.api.event.RecursoCriadoEvent;
import desafio.api.model.Venda;
import desafio.api.repository.VendaRepository;
import desafio.api.repository.filter.VendaFilter;
import desafio.api.service.VendaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Vendas")
@RestController
@RequestMapping("/vendas")
public class VendaResource {

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private VendaService vendaService;

	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@GetMapping
	@ApiOperation("Lista todos as vendas")
	public List<Venda> listar(VendaFilter vendaFilter) {

		return vendaRepository.filtrar(vendaFilter);
	}

	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Inserir vendas")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Venda> criar(@Valid @RequestBody Venda venda, HttpServletResponse response) {
	
		/*
		for (Produto produto : venda.getProdutos()) {
			produto = produtoRepository.findById(produto.getId()).orElse(null);
			if (venda.getFornecedor().getId() != produto.getFornecedor().getId()) {				
			}
			return ResponseEntity.badRequest().body(new Venda());
			}*/
			
		publisher.publishEvent(new RecursoCriadoEvent(this, response, venda.getId()));

		Venda vendaSalva = vendaService.salvar(venda);
		return ResponseEntity.status(HttpStatus.CREATED).body(vendaSalva);
	}

	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Buscar por ID")
	@GetMapping("/{Id}")
	public ResponseEntity<?> buscarPeloId(@PathVariable Long Id, HttpServletResponse response) {
		Optional<Venda> vendas = vendaRepository.findById(Id);

		return !vendas.isEmpty() ? ResponseEntity.ok(vendas) : ResponseEntity.notFound().build();

	}

	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Atualizar vendas")
	@PutMapping("/{Id}")
	public ResponseEntity<Venda> atualizar(@Valid @PathVariable Long Id, @RequestBody Venda venda) {

		if (!vendaRepository.existsById(Id)) {
			return ResponseEntity.notFound().build();
		}

		venda.setId(Id);
		venda = vendaRepository.save(venda);
		return ResponseEntity.ok(venda);
	}

	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Excluir vendas")
	@DeleteMapping("/{Id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long Id) {
		vendaRepository.deleteById(Id);
	}

	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Lista as vendas pela data em ordem crescente")
	@GetMapping("/asc")
	public List<Venda> listAsc(){
		return vendaRepository.findAll(Sort.by(Sort.Direction.ASC, "dataCompra"));
	}
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Lista as vendas pela data em ordem descrescente")
	@GetMapping("/desc")
	public List<Venda> listDesc(){
		return vendaRepository.findAll(Sort.by(Sort.Direction.DESC, "dataCompra"));
	}
	
}
