package br.com.rafael.syonet.model;

import java.io.Serializable;

/**
 * Classe abstrata de entidade.
 *
 * @author Rafael Braga
 *
 * @param <P> o tipo de dado da chave primária.
 */
public abstract class AbstractEntity<P extends Serializable> implements Serializable {

	/** Constante de serialização. */
	private static final long serialVersionUID = -3103675696559189708L;

	/**
	 * Retorna o identificador da entidade.
	 *
	 * @return o identificador da entidade.
	 */
	public abstract P getId();

}
