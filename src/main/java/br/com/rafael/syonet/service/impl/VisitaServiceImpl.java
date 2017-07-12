package br.com.rafael.syonet.service.impl;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.rafael.syonet.model.Vendedor;
import br.com.rafael.syonet.model.Visita;
import br.com.rafael.syonet.repository.VendedorRepository;
import br.com.rafael.syonet.repository.VisitaRepository;
import br.com.rafael.syonet.service.VisitaService;
import br.com.rafael.syonet.service.converter.VisitaConverter;
import br.com.rafael.syonet.service.dto.VisitaDTO;

/**
 * Classe de implementação de serviços de visita.
 *
 * @author Rafael Braga
 */
@Service
public class VisitaServiceImpl implements VisitaService {

	/** Repositório de visitas */
	@Autowired
	private VisitaRepository visitaRepository;

	/** Repositório de vendedores */
	@Autowired
	private VendedorRepository vendedorRepository;

	/** Converter de visita */
	@Autowired
	private VisitaConverter visitaConverter;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void salvar(final VisitaDTO visitaDTO) {
		if (visitaDTO != null && visitaDTO.getCidade() != null && visitaDTO.getData() != null) {
			final Visita visitaEntity = this.visitaConverter.convertToEntity(visitaDTO);
			this.escolherVendedor(visitaEntity);

			// Persiste a visita.
			this.visitaRepository.save(visitaEntity);
		}
	}

	/**
	 * Escolhe o vendedor para a visita.
	 *
	 * @param visitaEntity a {@link Visita}.
	 */
	protected void escolherVendedor(final Visita visitaEntity) {
		// Lista todas as visitas da semana...
		final List<Visita> visitasSemana = this.listarVisitasSemana(visitaEntity.getData(), new Sort("data"));

		// ...e verifica se já existe alguma visita agendada para a cidade.
		final Vendedor vendedor = this.verificarJaExisteVisitaCidade(visitaEntity, visitasSemana);

		// Se não existe visita para a cidade...
		if (vendedor == null) {
			// ...busca todos os vendedores...
			final List<Vendedor> vendedores = new ArrayList<>();
			final Iterator<Vendedor> iterator = this.vendedorRepository.findAll().iterator();
			iterator.forEachRemaining(vendedores::add);

			// ...e verifica quais entrarão para o sorteio da visita.
			final List<Vendedor> vendedoresParaSelecao = this.listarVendedoresDisponiveisParaVisita(visitasSemana, vendedores);

			// Sorteia um vendedor.
			final int indexSorteado = new Random().nextInt(vendedoresParaSelecao.size());
			visitaEntity.setVendedor(vendedoresParaSelecao.get(indexSorteado));
		} else {
			visitaEntity.setVendedor(vendedor);
		}
	}

	/**
	 * Lista os vendedores que estão disponíveis para a visita.
	 *
	 * @param visitasSemana a lista de {@link Visita} da semana.
	 * @param vendedores a lista dos {@link Vendedor} cadastrados.
	 * @return a lista de {@link Vendedor} para sorteio.
	 */
	protected List<Vendedor> listarVendedoresDisponiveisParaVisita(final List<Visita> visitasSemana, final List<Vendedor> vendedores) {
		final Set<Vendedor> vendedoresJaOcupados = new HashSet<>();
		for (final Visita visita : visitasSemana) {
			vendedoresJaOcupados.add(visita.getVendedor());
		}
		if (vendedoresJaOcupados.size() == vendedores.size()) {
			return vendedores;
		} else {
			vendedores.removeAll(vendedoresJaOcupados);
			return vendedores;
		}

	}

	/**
	 * Verifica se já existe uma visita agendada para a cidade, setando o mesmo vendedor.
	 *
	 * @param visitaEntity a {@link Visita} que se deseja agendar.
	 * @param visitasSemana a lista de {@link Visita} da semana.
	 * @return o vendedor que irá realizar visita na cidade.
	 */
	protected Vendedor verificarJaExisteVisitaCidade(final Visita visitaEntity, final List<Visita> visitasSemana) {
		for (final Visita visitaSemana : visitasSemana) {
			// Se existe uma visita agendada para a mesma cidade
			if (visitaEntity.getCidade().equals(visitaSemana.getCidade())) {
				// ...retorna o mesmo vendedor para a visita
				return visitaSemana.getVendedor();
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<VisitaDTO> listarVisitasSemana() {
		return this.visitaConverter.convertToDTOList(this.listarVisitasSemana(LocalDate.now(), new Sort("data")));
	}

	/**
	 * Lista as visitas agendadas para a semana da data informada.
	 *
	 * @param dataVisita a data desejada para a visita.
	 * @param sort a coluna para ordenação.
	 * @return a lista de {@link Visita}.
	 */
	protected List<Visita> listarVisitasSemana(final LocalDate dataVisita, final Sort sort) {
		final TemporalField fieldUS = WeekFields.of(Locale.US).dayOfWeek();
		final LocalDate dataInicial = dataVisita.with(fieldUS, 1);
		final LocalDate dataFinal = dataVisita.with(fieldUS, 1).plusDays(6L);
		return this.visitaRepository.findByDataBetween(dataInicial, dataFinal, sort);
	}

}
