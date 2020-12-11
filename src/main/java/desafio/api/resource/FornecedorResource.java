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
import desafio.api.model.Fornecedor;
import desafio.api.repository.FornecedorRepository;
import desafio.api.repository.filter.FornecedorFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(tags="Fornecedores")
@RestController
@RequestMapping("/fornecedores")
public class FornecedorResource {

	@Autowired
	private FornecedorRepository fornecedorRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@GetMapping
	@ApiOperation("Lista todos os fornecedores")
	public List<Fornecedor> listar(FornecedorFilter fornecedorFilter) {
		return fornecedorRepository.filtrar(fornecedorFilter);
	}

	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Inserir fornecedores")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Fornecedor> criar(@Valid @RequestBody Fornecedor fornecedor, HttpServletResponse response) {
		Fornecedor fornecedorSalvo = fornecedorRepository.save(fornecedor);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, fornecedorSalvo.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorSalvo);
	}

	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Atualizar fornecedores")
	@PutMapping("/{Id}")
	public ResponseEntity<Fornecedor> atualizar(@Valid @PathVariable Long Id, @RequestBody Fornecedor fornecedor) {

		if (!fornecedorRepository.existsById(Id)) {
			return ResponseEntity.notFound().build();
		}

		fornecedor.setId(Id);
		fornecedor = fornecedorRepository.save(fornecedor);
		return ResponseEntity.ok(fornecedor);
	}

	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Buscar por ID")
	@GetMapping("/{Id}")
	public ResponseEntity<?> buscarPeloId(@PathVariable Long Id, HttpServletResponse response) {
		Optional<Fornecedor> fornecedores = fornecedorRepository.findById(Id);

		return !fornecedores.isEmpty() ? ResponseEntity.ok(fornecedores) : ResponseEntity.notFound().build();

	}
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Excluir fornecedores")
	@DeleteMapping("/{Id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long Id) {
		fornecedorRepository.deleteById(Id);
	}
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Lista os fornecedores pelo nome em ordem crescente")
	@GetMapping("/asc")
	public List<Fornecedor> listAsc(){
		return fornecedorRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
	}
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Lista os fornecedores pelo nome em ordem descrescente")
	@GetMapping("/desc")
	public List<Fornecedor> listDesc(){
		return fornecedorRepository.findAll(Sort.by(Sort.Direction.DESC, "nome"));
	}
}
