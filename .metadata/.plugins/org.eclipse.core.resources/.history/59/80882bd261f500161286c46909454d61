package br.com.lotto.rest;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.lotto.dto.atrazoDTO;
import br.com.lotto.dto.frequenciaDTO;
import br.com.lotto.entity.Megasena;
import br.com.lotto.service.MegaSenaService;

@RestController
@RequestMapping(value = "/rest/megasena")
public class MegaSenaRestController {
	
	@Autowired
	private MegaSenaService mgaSenaService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Megasena>> listar() {
		return new ResponseEntity<>(mgaSenaService.buscartodos(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Megasena> buscarPorId(@PathVariable Integer id) {
		return new ResponseEntity<>(mgaSenaService.buscarPorId(id), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/frequencia", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<frequenciaDTO>> buscarFrequencias() {
		return new ResponseEntity<>(mgaSenaService.buscarFrequencias(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/atrazo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<atrazoDTO> > buscarAtrazos() {
		return new ResponseEntity<>(mgaSenaService.buscarAtrazos(), HttpStatus.OK);
	}

}
