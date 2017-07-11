package br.com.rafael.syonet.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafael.syonet.service.VisitaService;
import br.com.rafael.syonet.service.dto.VisitaDTO;

/**
 * Controller de visita.
 *
 * @author Rafael Braga
 */
@RestController
@RequestMapping("visita")
public class VisitaController {

	/** Serviços de visita */
	@Autowired
	private VisitaService visitaService;

	/**
	 * Salva uma visita na agenda.
	 *
	 * @param visitaDTO a {@link VisitaDTO}.
	 */
	@PutMapping
	@ResponseBody
	public void salvar(@RequestBody @Valid final VisitaDTO visitaDTO) {
		this.visitaService.salvar(visitaDTO);
	}

	/**
	 * Lista as visitas da semana atual.
	 *
	 * @return a lista de {@link VisitaDTO}.
	 * @throws ParseException exceção lançada em caso de erro.
	 */
	@GetMapping("semana")
	public ResponseEntity<List<VisitaDTO>> listarVisitasSemana() throws ParseException {
		return new ResponseEntity<>(this.visitaService.listarVisitasSemana(), HttpStatus.OK);
	}

}
