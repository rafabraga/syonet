package br.com.rafael.syonet.service;

import java.text.ParseException;
import java.util.List;

import br.com.rafael.syonet.service.dto.VisitaDTO;

/**
 * Interface de serviços de visita.
 *
 * @author Rafael Braga
 */
public interface VisitaService {

	/**
	 * Salva uma visita na agenda.
	 *
	 * @param visitaDTO a {@link VisitaDTO}.
	 */
	public void salvar(final VisitaDTO visitaDTO);

	/**
	 * Lista as visitas agendadas para a semana atual.
	 *
	 * @return a lista de {@link VisitaDTO}.
	 * @throws ParseException exceção lançada em caso de erro.
	 */
	public List<VisitaDTO> listarVisitasSemana() throws ParseException;

}
