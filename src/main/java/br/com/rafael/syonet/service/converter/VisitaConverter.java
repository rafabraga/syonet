package br.com.rafael.syonet.service.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.rafael.syonet.model.Visita;
import br.com.rafael.syonet.service.dto.VisitaDTO;

@Component
public class VisitaConverter extends AbstractConverter<Visita, VisitaDTO> {

	@Autowired
	private VendedorConverter vendedorConverter;

	@Override
	public Visita convertToEntity(VisitaDTO dto) {
		final Visita entity = new Visita();
		entity.setId(dto.getId());
		entity.setData(dto.getData());
		entity.setCidade(dto.getCidade());
		return entity;
	}

	@Override
	public VisitaDTO convertToDTO(Visita entity) {
		final VisitaDTO dto = new VisitaDTO();
		dto.setId(entity.getId());
		dto.setData(entity.getData());
		dto.setCidade(entity.getCidade());
		dto.setVendedor(
				entity.getVendedor() == null ? null : this.vendedorConverter.convertToDTO(entity.getVendedor()));
		return dto;
	}

}
