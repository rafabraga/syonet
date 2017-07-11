package br.com.rafael.syonet.service.converter;

import org.springframework.stereotype.Component;

import br.com.rafael.syonet.model.Vendedor;
import br.com.rafael.syonet.service.dto.VendedorDTO;

/**
 * Converter da entidade {@link Vendedor}.
 *
 * @author Rafael Braga
 */
@Component
public class VendedorConverter extends AbstractConverter<Vendedor, VendedorDTO> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vendedor convertToEntity(final VendedorDTO dto) {
		final Vendedor entity = new Vendedor();
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		return entity;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VendedorDTO convertToDTO(final Vendedor entity) {
		final VendedorDTO dto = new VendedorDTO();
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		return dto;
	}

}
