package br.com.rafael.syonet.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entidade que representa um vendedor.
 *
 * @author Rafael Braga
 */
@Entity
@Table
public class Vendedor extends AbstractEntity<Long> {

	/** Constante de serialização */
	private static final long serialVersionUID = -6698253408065351312L;

	/** Identificador do vendedor */
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** Nome do vendedor */
	@Column
	private String nome;

	/** Lista de visitas agendadas para o vendedor */
	@OneToMany(mappedBy = "vendedor")
	private List<Visita> visitas;

	/**
	 * @return the id
	 */
	@Override
	public Long getId() {
		return this.id;
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

	/**
	 * @return the visitas
	 */
	public List<Visita> getVisitas() {
		return this.visitas;
	}

	/**
	 * @param visitas the visitas to set
	 */
	public void setVisitas(final List<Visita> visitas) {
		this.visitas = visitas;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
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
		if (!(obj instanceof Vendedor)) {
			return false;
		}
		final Vendedor other = (Vendedor) obj;
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
