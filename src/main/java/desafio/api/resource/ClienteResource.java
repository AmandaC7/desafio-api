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
import org.springframework.security.crypto.bcrypt.BCrypt;
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
import desafio.api.model.Cliente;
import desafio.api.repository.ClienteRepository;
import desafio.api.repository.filter.ClienteFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(tags="Clientes")
@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteRepository clienteRepository;

	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@GetMapping
	@ApiOperation("Lista todos os clientes")
	public List<Cliente> listar(ClienteFilter clienteFilter) {
		return clienteRepository.filtrar( clienteFilter);
	}

	@Autowired
	private ApplicationEventPublisher publisher;

	@ApiOperation("Inserir clientes")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Cliente> criar(@Valid @RequestBody Cliente cliente, HttpServletResponse response) {

		
		Cliente clienteSalvo = clienteRepository.save(cliente);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, clienteSalvo.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
	}

	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Buscar por ID")
	@GetMapping("/{Id}")
	public ResponseEntity<?> buscarPeloId(@PathVariable Long Id, HttpServletResponse response) {
		Optional<Cliente> clientes = clienteRepository.findById(Id);

		return !clientes.isEmpty() ? ResponseEntity.ok(clientes) : ResponseEntity.notFound().build();

	}

	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Atualizar clientes")
	@PutMapping("/{Id}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long Id, @RequestBody Cliente cliente) {

		if (!clienteRepository.existsById(Id)) {
			return ResponseEntity.notFound().build();
		}

		cliente.setId(Id);
		cliente = clienteRepository.save(cliente);
		return ResponseEntity.ok(cliente);
	}

	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Excluir clientes")
	@DeleteMapping("/{Id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long Id) {
		clienteRepository.deleteById(Id);
	}
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Lista os clientes pelo nome em ordem crescente")
	@GetMapping("/asc")
	public List<Cliente> listAsc(){
		return clienteRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
	}
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Lista os clientes pelo nome em ordem descrescente")
	@GetMapping("/desc")
	public List<Cliente> listDesc(){
		return clienteRepository.findAll(Sort.by(Sort.Direction.DESC, "nome"));
	}

}
