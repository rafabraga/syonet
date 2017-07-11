package br.com.rafael.syonet.repository;

import java.util.Date;
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

	List<Visita> findByDataBetween(Date dataInicial, Date dataFinal, Sort sort);

}
