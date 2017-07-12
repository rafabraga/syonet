package br.com.rafael.syonet.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Sort;

import br.com.rafael.syonet.model.Vendedor;
import br.com.rafael.syonet.model.Visita;
import br.com.rafael.syonet.repository.VendedorRepository;
import br.com.rafael.syonet.repository.VisitaRepository;
import br.com.rafael.syonet.service.converter.VisitaConverter;
import br.com.rafael.syonet.service.dto.VisitaDTO;
import br.com.rafael.syonet.util.AbstractBaseUnitTest;
import br.com.rafael.syonet.util.UnitTestUtil;

public class VisitaServiceImplTest extends AbstractBaseUnitTest<VisitaServiceImpl> {

	/** Repositório de visitas */
	@Mock
	private VisitaRepository visitaRepository;

	/** Repositório de vendedores */
	@Mock
	private VendedorRepository vendedorRepository;

	/** Converter de visita */
	@Mock
	private VisitaConverter visitaConverter;

	/**
	 * Testa o método {@link VisitaServiceImpl#salvar(VisitaDTO)} com uma visita nula.
	 */
	@Test
	public void salvarVisitaNullTest() {
		this.testClass.salvar(null);
		Mockito.verify(this.visitaRepository, Mockito.never()).save(Matchers.any(Visita.class));
	}

	/**
	 * Testa o método {@link VisitaServiceImpl#salvar(VisitaDTO)} com uma visita sem cidade.
	 */
	@Test
	public void salvarCidadeVisitaNullTest() {
		this.testClass.salvar(new VisitaDTO());
		Mockito.verify(this.visitaRepository, Mockito.never()).save(Matchers.any(Visita.class));
	}

	/**
	 * Testa o método {@link VisitaServiceImpl#salvar(VisitaDTO)} com uma visita sem data.
	 */
	@Test
	public void salvarDataVisitaNullTest() {
		final VisitaDTO visita = UnitTestUtil.criarVisitaDTO("POA");
		visita.setData(null);
		this.testClass.salvar(visita);
		Mockito.verify(this.visitaRepository, Mockito.never()).save(Matchers.any(Visita.class));
	}

	/**
	 * Testa o método {@link VisitaServiceImpl#salvar(VisitaDTO)} com uma visita nula.
	 */
	@Test
	public void salvarSuccessTest() {
		Mockito.doNothing().when(this.testClass).escolherVendedor(Matchers.any(Visita.class));
		Mockito.doReturn(new Visita()).when(this.visitaConverter).convertToEntity(Matchers.any(VisitaDTO.class));
		Mockito.doReturn(new Visita()).when(this.visitaRepository).save(Matchers.any(Visita.class));
		final VisitaDTO visita = UnitTestUtil.criarVisitaDTO("POA");
		this.testClass.salvar(visita);
		Mockito.verify(this.visitaRepository).save(Matchers.any(Visita.class));
		Mockito.verify(this.testClass).escolherVendedor(Matchers.any(Visita.class));
	}

	/**
	 * Testa o método {@link VisitaServiceImpl#listarVendedoresDisponiveisParaVisita(List, List)} com um vendedor disponível.
	 */
	@Test
	public void listarVendedoresDisponiveisParaVisitaUmVendedorDisponivelTest() {
		final List<Vendedor> vendedores = UnitTestUtil.criarListaVendedor(1L, 2L);
		final List<Visita> visitasSemana = new ArrayList<>();
		final Visita visita1 = new Visita();
		visita1.setVendedor(vendedores.get(0));
		visitasSemana.add(visita1);
		final List<Vendedor> actual = this.testClass.listarVendedoresDisponiveisParaVisita(visitasSemana, vendedores);
		Assert.assertEquals(1, actual.size());
		Assert.assertEquals(2, actual.iterator().next().getId().longValue());
	}

	/**
	 * Testa o método {@link VisitaServiceImpl#listarVendedoresDisponiveisParaVisita(List, List)} com todos os vendedores ocupados.
	 */
	@Test
	public void listarVendedoresDisponiveisParaVisitaTodosVendedoresParaSorteioTest() {
		final List<Vendedor> vendedores = UnitTestUtil.criarListaVendedor(1L, 2L);
		final List<Visita> visitasSemana = new ArrayList<>();
		final Visita visita1 = new Visita();
		visita1.setVendedor(vendedores.get(0));
		visitasSemana.add(visita1);
		final Visita visita2 = new Visita();
		visita1.setVendedor(vendedores.get(1));
		visitasSemana.add(visita2);
		final List<Vendedor> actual = this.testClass.listarVendedoresDisponiveisParaVisita(visitasSemana, vendedores);
		Assert.assertEquals(2, actual.size());
		Assert.assertEquals(1, actual.get(0).getId().longValue());
		Assert.assertEquals(2, actual.get(1).getId().longValue());
	}

	/**
	 * Testa o método {@link VisitaServiceImpl#verificarJaExisteVisitaCidade(Visita, List)} com nenhuma visita para a cidade.
	 */
	@Test
	public void verificarJaExisteVisitaCidadeNaoExisteTest() {
		final List<Visita> visitasSemana = UnitTestUtil.criarListaVisita("POA");
		final Visita visitaEntity = UnitTestUtil.criarVisita("CAÍ");
		final Vendedor actual = this.testClass.verificarJaExisteVisitaCidade(visitaEntity, visitasSemana);
		Assert.assertNull(actual);
	}

	/**
	 * Testa o método {@link VisitaServiceImpl#verificarJaExisteVisitaCidade(Visita, List)} com uma visita já agendada para a cidade.
	 */
	@Test
	public void verificarJaExisteVisitaCidadeExisteTest() {
		final List<Visita> visitasSemana = UnitTestUtil.criarListaVisita("POA");
		final Visita visitaEntity = UnitTestUtil.criarVisita("POA");
		final Vendedor actual = this.testClass.verificarJaExisteVisitaCidade(visitaEntity, visitasSemana);
		Assert.assertNotNull(actual);
	}

	/**
	 * Testa o método {@link VisitaServiceImpl#listarVisitasSemana()}.
	 */
	@Test
	public void listarVisitasSemanaTest() {
		Mockito.doReturn(new ArrayList<VisitaDTO>()).when(this.visitaConverter).convertToDTOList(Matchers.anyListOf(Visita.class));
		Mockito.doReturn(new ArrayList<Visita>()).when(this.testClass).listarVisitasSemana(Matchers.any(LocalDate.class), Matchers.any(Sort.class));
		final List<VisitaDTO> actual = this.testClass.listarVisitasSemana();
		Assert.assertNotNull(actual);
	}

	/**
	 * Testa o método {@link VisitaServiceImpl#escolherVendedor(Visita)} quando já há um vendedor para a visita na cidade.
	 */
	@Test
	public void escolherVendedorComVendedorParaCidadeTest() {
		Mockito.doReturn(UnitTestUtil.criarListaVisita("POA")).when(this.testClass).listarVisitasSemana(Matchers.any(LocalDate.class),
		        Matchers.any(Sort.class));
		Mockito.doReturn(UnitTestUtil.criarVendedor(1L)).when(this.testClass).verificarJaExisteVisitaCidade(Matchers.any(Visita.class),
		        Matchers.anyListOf(Visita.class));
		final Visita visita = UnitTestUtil.criarVisita("POA");
		this.testClass.escolherVendedor(visita);
		Mockito.verify(this.testClass, Mockito.never()).listarVendedoresDisponiveisParaVisita(Matchers.anyListOf(Visita.class),
		        Matchers.anyListOf(Vendedor.class));
		Assert.assertNotNull(visita.getVendedor());
	}

	/**
	 * Testa o método {@link VisitaServiceImpl#escolherVendedor(Visita)} quando ainda não há um vendedor para a visita na cidade.
	 */
	@Test
	public void escolherVendedorSemVendedorParaCidadeTest() {
		Mockito.doReturn(UnitTestUtil.criarListaVisita("POA")).when(this.testClass).listarVisitasSemana(Matchers.any(LocalDate.class),
		        Matchers.any(Sort.class));
		Mockito.doReturn(null).when(this.testClass).verificarJaExisteVisitaCidade(Matchers.any(Visita.class), Matchers.anyListOf(Visita.class));
		final Visita visita = UnitTestUtil.criarVisita("POA");
		final Iterable<Vendedor> iterableVendedores = UnitTestUtil.criarListaVendedor(1L, 2L);
		Mockito.doReturn(iterableVendedores).when(this.vendedorRepository).findAll();
		Mockito.doReturn(UnitTestUtil.criarListaVendedor(1L)).when(this.testClass)
		        .listarVendedoresDisponiveisParaVisita(Matchers.anyListOf(Visita.class), Matchers.anyListOf(Vendedor.class));
		this.testClass.escolherVendedor(visita);
		Mockito.verify(this.testClass).listarVendedoresDisponiveisParaVisita(Matchers.anyListOf(Visita.class), Matchers.anyListOf(Vendedor.class));
		Assert.assertNotNull(visita.getVendedor());
	}

	/**
	 * Testa o método {@link VisitaServiceImpl#listarVisitasSemana(LocalDate, Sort)}.
	 */
	@Test
	public void listarVisitasSemanaComParametrosTest() {
		Mockito.doReturn(UnitTestUtil.criarListaVisita("POA")).when(this.visitaRepository).findByDataBetween(Matchers.any(LocalDate.class),
		        Matchers.any(LocalDate.class), Matchers.any(Sort.class));
		final LocalDate data = LocalDate.of(2017, 07, 11);
		final Sort sort = new Sort("data");
		final List<Visita> actual = this.testClass.listarVisitasSemana(data, sort);
		Assert.assertNotNull(actual);
		Mockito.verify(this.visitaRepository).findByDataBetween(LocalDate.of(2017, 07, 9), LocalDate.of(2017, 07, 15), sort);
	}

}
