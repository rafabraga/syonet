package br.com.rafael.syonet.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.rafael.syonet.model.Visita;

/**
 * Repositório para operações de {@link Visita}.
 *
 * @author Rafael Braga
 */
@Repository
public interface VisitaRepository extends CrudRepository<Visita, Long> {

	/**
	 * Lista as visitas agendadas entre duas datas.
	 *
	 * @param dataInicial a data inicial.
	 * @param dataFinal a data final.
	 * @param sort a ordenação.
	 * @return a lista de {@link Visita}.
	 */
	List<Visita> findByDataBetween(LocalDate dataInicial, LocalDate dataFinal, Sort sort);

}
