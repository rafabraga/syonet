package br.com.rafael.syonet.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Date;
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
import br.com.rafael.syonet.service.converter.VendedorConverter;
import br.com.rafael.syonet.service.converter.VisitaConverter;
import br.com.rafael.syonet.service.dto.VendedorDTO;
import br.com.rafael.syonet.service.dto.VisitaDTO;

@Service
public class VisitaService {

	@Autowired
	private VisitaRepository visitaRepository;

	@Autowired
	private VendedorRepository vendedorRepository;

	@Autowired
	private VisitaConverter visitaConverter;

	@Autowired
	private VendedorConverter vendedorConverter;

	public void salvar(final VisitaDTO visitaDTO) {
		final Visita visitaEntity = this.visitaConverter.convertToEntity(visitaDTO);
		this.escolherVendedor(visitaEntity, visitaDTO.getCidade());

		// Persiste a visita.
		this.visitaRepository.save(visitaEntity);
	}

	private void escolherVendedor(Visita visitaEntity, final String cidadeVisita) {
		try {
			// Lista todas as visitas da semana...
			final List<VisitaDTO> visitasSemana = this.listarVisitasSemana(visitaEntity.getData());

			// ...e verifica se já existe alguma visita agendada para a cidade.
			this.verificarJaExisteVisitaCidade(visitaEntity, visitasSemana, cidadeVisita);

			// Se não existe visita para a cidade...
			if (visitaEntity.getVendedor() == null) {

				// ...busca todos os vendedores...
				final List<Vendedor> vendedores = new ArrayList<>();
				final Iterator<Vendedor> iterator = this.vendedorRepository.findAll().iterator();
				iterator.forEachRemaining(vendedores::add);
				final List<VendedorDTO> vendedoresDTO = this.vendedorConverter.convertToDTOList(vendedores);

				// ...e verifica quem já tem visitas para a semana e quem não
				// tem.
				this.verificarVendedoresJaOcupados(visitasSemana, vendedores, vendedoresDTO);

				// Sorteia um vendedor.
				final int indexSorteado = new Random().nextInt(vendedoresDTO.size());
				final Vendedor vendedor = this.vendedorConverter.convertToEntity(vendedoresDTO.get(indexSorteado));
				visitaEntity.setVendedor(vendedor);
			}
		} catch (final ParseException e) {
			e.printStackTrace();
		}
	}

	protected void verificarVendedoresJaOcupados(final List<VisitaDTO> visitasSemana, final List<Vendedor> vendedores,
			final List<VendedorDTO> vendedoresDTO) {
		final Set<VendedorDTO> vendedoresJaOcupados = new HashSet<>();
		for (final VisitaDTO visitaSemana : visitasSemana) {
			vendedoresJaOcupados.add(visitaSemana.getVendedor());
		}

		if (vendedoresJaOcupados.size() != vendedores.size()) {
			vendedoresDTO.removeAll(vendedoresJaOcupados);
		}
	}

	protected void verificarJaExisteVisitaCidade(final Visita visitaEntity, final List<VisitaDTO> visitasSemana,
			final String cidadeVisita) {
		for (final VisitaDTO visitaSemana : visitasSemana) {
			// Se existe uma visita agendada para a mesma cidade
			if (cidadeVisita.equals(visitaSemana.getCidade())) {
				final Vendedor vendedor = this.vendedorRepository.findOne(visitaSemana.getVendedor().getId());
				// Seta o mesmo vendedor para a visita
				visitaEntity.setVendedor(vendedor);
				break;
			}
		}
	}

	public List<VisitaDTO> listarVisitasSemana() throws ParseException {
		return this.listarVisitasSemana(new Date());
	}

	protected List<VisitaDTO> listarVisitasSemana(final Date dataVisita) throws ParseException {
		final LocalDate data = dataVisita.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		final TemporalField fieldUS = WeekFields.of(Locale.US).dayOfWeek();
		final Date dataInicial = new SimpleDateFormat("yyyy-MM-dd").parse(data.with(fieldUS, 1).toString());
		final Date dataFinal = new SimpleDateFormat("yyyy-MM-dd").parse(data.with(fieldUS, 1).plusDays(6L).toString());
		return this.visitaConverter
				.convertToDTOList(this.visitaRepository.findByDataBetween(dataInicial, dataFinal, new Sort("data")));
	}

}
