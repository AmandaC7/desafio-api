package desafio.api.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class RecursoCriadoEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private HttpServletResponse response;
	private Long Id;
	
	public RecursoCriadoEvent(Object source, HttpServletResponse response, Long Id) {
		super(source);
		this.response = response;
		this.Id = Id;
	}


	public HttpServletResponse getResponse() {
		return response;
	}

	public Long getId() {
		return Id;
	}

	
}
