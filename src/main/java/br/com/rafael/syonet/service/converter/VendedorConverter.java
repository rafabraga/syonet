package br.com.rafael.syonet.service.converter;

import org.springframework.stereotype.Component;

import br.com.rafael.syonet.model.Vendedor;
import br.com.rafael.syonet.service.dto.VendedorDTO;

@Component
public class VendedorConverter extends AbstractConverter<Vendedor, VendedorDTO> {

	@Override
	public Vendedor convertToEntity(VendedorDTO dto) {
		final Vendedor entity = new Vendedor();
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		return entity;
	}

	@Override
	public VendedorDTO convertToDTO(Vendedor entity) {
		final VendedorDTO dto = new VendedorDTO();
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		return dto;
	}

}
