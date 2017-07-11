package br.com.rafael.syonet.service.converter;

import java.io.Serializable;
import java.util.List;

import br.com.rafael.syonet.model.AbstractEntity;
import br.com.rafael.syonet.service.dto.AbstractDTO;

/**
 * Interface contendo métodos para conversão entre entidade e DTO.
 *
 * @author Rafael Braga
 *
 * @param <E> a classe da entidade.
 * @param <D> a classe de DTO.
 */
public interface Converter<E extends AbstractEntity<? extends Serializable>, D extends AbstractDTO> {

	/**
	 * Converte DTO para entidade.
	 *
	 * @param dto o DTO.
	 * @return a entidade.
	 */
	public E convertToEntity(D dto);

	/**
	 * Converte entidade para DTO.
	 *
	 * @param entity a entidade.
	 * @return o DTO.
	 */
	public D convertToDTO(E entity);

	/**
	 * Converte uma lista de DTOs para uma lista de entidades.
	 *
	 * @param dtos a lista de DTOs.
	 * @return a lista de entidades.
	 */
	public List<E> convertToEntityList(List<D> dtos);

	/**
	 * Converte uma lista de entidades para uma lista de DTOs.
	 *
	 * @param entities a lista de entidades.
	 * @return a lista de DTOs.
	 */
	public List<D> convertToDTOList(List<E> entities);

}
