package desafio.api.model;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;



@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@NotNull
	private String nome;
	
	@NotNull
	@Column(name = "codigo_produto")
	private String codigoProduto;
		
	@NotNull
	@DecimalMax(value = "9999999999.99", message = "Valor não pode ser maior que 9.999.999,9")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valor;
	
	private Boolean promocao;
	
	@DecimalMax(value = "9999999999.99", message = "Valor não pode ser maior que 9.999.999,9")
	@Column(name="valor_promocao")
	private BigDecimal valorPromocao;
	
	@NotNull
	private String categoria;
	
	private byte[] imagem;
	
	@NotNull
	private Long quantidade;
		
	@NotNull
	@ManyToOne
	@JoinColumn(name = "Id_fornecedor")
	private Fornecedor fornecedor;	
	
	@ManyToMany(mappedBy = "produtos")
	private Set<Venda> vendas;



	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public String getCodigo_produto() {
		return codigoProduto;
	}

	public void setCodigo_produto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public BigDecimal getValorPromocao() {
		return valorPromocao;
	}

	public void setValor_promo(BigDecimal valorPromocao) {
		this.valorPromocao = valorPromocao;
	}

	public Boolean getPromocao() {
		return promocao;
	}

	public void setPromocao(Boolean promocao) {
		this.promocao = promocao;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}


	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}


	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}

}
