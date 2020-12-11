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
import desafio.api.model.Produto;
import desafio.api.repository.ProdutoRepository;
import desafio.api.repository.filter.ProdutoFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(tags="Produtos")
@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@GetMapping
	@ApiOperation("Lista todos os produtos")
	public List<Produto> listar(ProdutoFilter produtoFilter) {
		return produtoRepository.filtrar(produtoFilter);
	}

	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Inserir produtos")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Produto> criar(@Valid @RequestBody Produto produto, HttpServletResponse response) {
		Produto produtoSalvo = produtoRepository.save(produto);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, produtoSalvo.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
	}

	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Buscar por ID")
	@GetMapping("/{Id}")
	public ResponseEntity<?> buscarPeloId(@PathVariable Long Id, HttpServletResponse response) {
		Optional<Produto> produtos = produtoRepository.findById(Id);

		return !produtos.isEmpty() ? ResponseEntity.ok(produtos) : ResponseEntity.notFound().build();

	}

	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Atualizar produtos")
	@PutMapping("/{Id}")
	public ResponseEntity<Produto> atualizar(@Valid @PathVariable Long Id, @RequestBody Produto produto) {

		if (!produtoRepository.existsById(Id)) {
			return ResponseEntity.notFound().build();
		}

		produto.setId(Id);
		produto = produtoRepository.save(produto);
		return ResponseEntity.ok(produto);
	}

	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Excluir produtos")
	@DeleteMapping("/{Id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long Id) {
		produtoRepository.deleteById(Id);
	}
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Lista os produtos pelo nome em ordem crescente")
	@GetMapping("/asc")
	public List<Produto> listAsc(){
		return produtoRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
	}
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Lista os produtos pelo nome em ordem descrescente")
	@GetMapping("/desc")
	public List<Produto> listDesc(){
		return produtoRepository.findAll(Sort.by(Sort.Direction.DESC, "nome"));
	}
}
