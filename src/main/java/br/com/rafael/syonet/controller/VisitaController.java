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

@RestController
@RequestMapping("visita")
public class VisitaController {

	@Autowired
	private VisitaService visitaService;

	@PutMapping
	@ResponseBody
	public void salvar(@RequestBody @Valid final VisitaDTO visitaDTO) {
		this.visitaService.salvar(visitaDTO);
	}

	@GetMapping("semana")
	public ResponseEntity<List<VisitaDTO>> listarVisitasSemana() throws ParseException {
		return new ResponseEntity<List<VisitaDTO>>(this.visitaService.listarVisitasSemana(), HttpStatus.OK);
	}

}
