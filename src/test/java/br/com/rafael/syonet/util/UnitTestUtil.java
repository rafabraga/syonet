package br.com.rafael.syonet.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.rafael.syonet.model.Vendedor;
import br.com.rafael.syonet.model.Visita;
import br.com.rafael.syonet.service.dto.VisitaDTO;

/**
 * Responsável por criar objetos que serão utilizados nos testes Unitários.
 *
 * @author Rafael Braga
 */
public class UnitTestUtil {

	/**
	 * Cria uma lista de {@link Visita}.
	 *
	 * @param cidades as cidades onde serão feitas as visitas.
	 * @return a lista de {@link Visita}.
	 */
	public static List<Visita> criarListaVisita(final String... cidades) {
		final List<Visita> lista = new ArrayList<>();
		for (final String cidade : cidades) {
			lista.add(criarVisita(cidade));
		}
		return lista;
	}

	/**
	 * Cria uma {@link Visita}.
	 *
	 * @param cidade a cidade onde será feita a visita.
	 * @return a {@link Visita}.
	 */
	public static Visita criarVisita(final String cidade) {
		final Visita visita = new Visita();
		visita.setCidade(cidade);
		visita.setData(LocalDate.now());
		visita.setVendedor(criarVendedor(1L));
		return visita;
	}

	/**
	 * Cria uma {@link VisitaDTO}.
	 *
	 * @param cidade a cidade onde será feita a visita.
	 * @return a {@link VisitaDTO}.
	 */
	public static VisitaDTO criarVisitaDTO(final String cidade) {
		final VisitaDTO visita = new VisitaDTO();
		visita.setCidade(cidade);
		visita.setData(LocalDate.now());
		return visita;
	}

	/**
	 * Cria uma lista de {@link Vendedor}.
	 *
	 * @param ids os identificadores dos vendedores.
	 * @return a lista de {@link Vendedor}.
	 */
	public static List<Vendedor> criarListaVendedor(final Long... ids) {
		final List<Vendedor> lista = new ArrayList<>();
		for (final Long id : ids) {
			lista.add(criarVendedor(id));
		}
		return lista;
	}

	/**
	 * Cria um {@link Vendedor}.
	 *
	 * @param id o identificador do vendedor.
	 * @return o {@link Vendedor}.
	 */
	public static Vendedor criarVendedor(final Long id) {
		final Vendedor vendedor = new Vendedor();
		vendedor.setId(id);
		return vendedor;
	}

}
