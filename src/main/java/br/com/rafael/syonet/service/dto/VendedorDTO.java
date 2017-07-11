package br.com.rafael.syonet.service.dto;

import br.com.rafael.syonet.model.Vendedor;

/**
 * DTO da entidade {@link Vendedor}.
 *
 * @author Rafael Braga
 */
public class VendedorDTO extends AbstractDTO {

	/** Constante de serialização */
	private static final long serialVersionUID = -146828875638147759L;

	/** Identificador do vendedor */
	private Long id;

	/** Nome do vendedor */
	private String nome;

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
	 * @return the nome
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(final String nome) {
		this.nome = nome;
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
		if (!(obj instanceof VendedorDTO)) {
			return false;
		}
		final VendedorDTO other = (VendedorDTO) obj;
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
