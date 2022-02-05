package br.com.ot.rest.ms;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ot.dto.AtrasoDTO;
import br.com.ot.dto.Ppt;
import br.com.ot.service.ServiceException;
import br.com.ot.service.ms.atraso.AtrasoService;

@RestController
@RequestMapping(value = "/rest/ms/atraso")
public class AtrasoRestController {

	@Autowired
	private AtrasoService atrasoService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<AtrasoDTO>> buscarAtrasos() {
		return new ResponseEntity<>(atrasoService.buscarAtrasos(), HttpStatus.OK);
	}

	@GetMapping(value = "maxconc/{max}/num/{num}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<AtrasoDTO>> buscarAtrasos(@PathVariable("max") Integer max,
			@PathVariable("num") Integer num) {
		return new ResponseEntity<>(this.atrasoService.buscarAtrasos(max, num), HttpStatus.OK);
	}

	@GetMapping(value = "/maxconc/{max}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<AtrasoDTO>> buscarAtrasos(@PathVariable("max") Integer max) {
		return new ResponseEntity<>(this.atrasoService.buscarAtrasos(max, 0), HttpStatus.OK);
	}

	@GetMapping(value = "/num/{num}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<AtrasoDTO>> buscarAtrasosNum(@PathVariable("num") Integer num) {
		return new ResponseEntity<>(this.atrasoService.buscarAtrasosNum(num), HttpStatus.OK);
	}

	@GetMapping(value = "/analisarAtraso", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<Object, Object>> analisarAtraso() {
		return new ResponseEntity<>(this.atrasoService.analisarAtraso(), HttpStatus.OK);
	}

	@GetMapping(value = "analisar-recursivo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<AtrasoDTO>> validarRecursivo(@RequestBody Ppt ppt)
			throws ServiceException {
		return new ResponseEntity<>(this.atrasoService.analizarRecursivo(ppt), HttpStatus.OK);
	}

}
