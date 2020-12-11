package desafio.api.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import desafio.api.event.RecursoCriadoEvent;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent>{

	@Override
	public void onApplicationEvent(RecursoCriadoEvent recursoCriadoEvent) {
		HttpServletResponse response = recursoCriadoEvent.getResponse();
		Long Id = recursoCriadoEvent.getId();
		
		adicionarHeaderLocation(response, Id);
		
	}

	private void adicionarHeaderLocation(HttpServletResponse response, Long Id) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{Id}")
				.buildAndExpand(Id).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}

}
