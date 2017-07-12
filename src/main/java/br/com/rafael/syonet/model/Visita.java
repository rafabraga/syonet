package br.com.rafael.syonet.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entidade que representa uma visita de um vendedor.
 *
 * @author Rafael Braga
 */
@Entity
@Table
public class Visita extends AbstractEntity<Long> {

	/** Constante de serialização */
	private static final long serialVersionUID = 4183773701985742740L;

	/** Identificador da visita */
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** Data da visita */
	@Column
	private LocalDate data;

	/** Cidade onde será feita a visita */
	@Column
	private String cidade;

	/** Vendedor que irá realizar a visita */
	@ManyToOne
	private Vendedor vendedor;

	/**
	 * @return the id
	 */
	@Override
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
	public Vendedor getVendedor() {
		return this.vendedor;
	}

	/**
	 * @param vendedor the vendedor to set
	 */
	public void setVendedor(final Vendedor vendedor) {
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
		if (!(obj instanceof Visita)) {
			return false;
		}
		final Visita other = (Visita) obj;
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
