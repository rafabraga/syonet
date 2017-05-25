package br.com.rafael.syonet.service.dto;

import java.util.Date;

public class VisitaDTO extends AbstractDTO {

	private static final long serialVersionUID = 4912733789590804686L;

	private Long id;
	private Date data;
	private String cidade;
	private VendedorDTO vendedor;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public VendedorDTO getVendedor() {
		return this.vendedor;
	}

	public void setVendedor(VendedorDTO vendedor) {
		this.vendedor = vendedor;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

}
