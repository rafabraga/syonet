package br.com.rafael.syonet.service.dto;

import java.time.LocalDate;

/**
 * DTO da entidade {@link VisitaDTO}.
 *
 * @author Rafael Braga
 */
public class VisitaDTO extends AbstractDTO {

	/** Constante de serialização */
	private static final long serialVersionUID = 4912733789590804686L;

	/** Identificador da visita */
	private Long id;

	/** Data da visita */
	private LocalDate data;

	/** Cidade onde será feita a visita */
	private String cidade;

	/** Vendedor que irá realizar a visita */
	private VendedorDTO vendedor;

	/**
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @return the data
	 */
	public LocalDate getData() {
		return this.data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(final LocalDate data) {
		this.data = data;
	}

	/**
	 * @return the cidade
	 */
	public String getCidade() {
		return this.cidade;
	}

	/**
	 * @param cidade the cidade to set
	 */
	public void setCidade(final String cidade) {
		this.cidade = cidade;
	}

	/**
	 * @return the vendedor
	 */
	public VendedorDTO getVendedor() {
		return this.vendedor;
	}

	/**
	 * @param vendedor the vendedor to set
	 */
	public void setVendedor(final VendedorDTO vendedor) {
		this.vendedor = vendedor;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.id == null ? 0 : this.id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof VisitaDTO)) {
			return false;
		}
		final VisitaDTO other = (VisitaDTO) obj;
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
