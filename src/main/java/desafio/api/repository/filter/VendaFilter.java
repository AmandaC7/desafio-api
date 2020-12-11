package desafio.api.repository.filter;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class VendaFilter {
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataCompraDe;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataCompraAte;
	
	
	public Date getDataCompraDe() {
		return dataCompraDe;
	}
	public void setDataCompraDe(Date dataCompraDe) {
		this.dataCompraDe = dataCompraDe;
	}
	public Date getDataCompraAte() {
		return dataCompraAte;
	}
	public void setDataCompraAte(Date dataCompraAte) {
		this.dataCompraAte = dataCompraAte;
	}
	
	
	
	
	

	
	
	
}
