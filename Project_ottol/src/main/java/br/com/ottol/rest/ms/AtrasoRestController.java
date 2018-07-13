package br.com.ottol.rest.ms;

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

import br.com.ottol.dto.AtrasoDTO;
import br.com.ottol.service.ms.atraso.AtrasoService;

@RestController
@RequestMapping(value = "/rest/ms/atraso")
public class AtrasoRestController {

	@Autowired
	private AtrasoService atrasoService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<AtrasoDTO>> buscarAtrasos() {
		return new ResponseEntity<>(atrasoService.buscarAtrasos(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "maxconc/{max}/num/{num}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<AtrasoDTO>> buscarAtrasos(@PathVariable("max") Integer max,
			@PathVariable("num") Integer num) {
		return new ResponseEntity<>(this.atrasoService.buscarAtrasos(max, num), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/maxconc/{max}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<AtrasoDTO>> buscarAtrasos(@PathVariable("max") Integer max) {
		return new ResponseEntity<>(this.atrasoService.buscarAtrasos(max, 0), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/num/{num}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<AtrasoDTO>> buscarAtrasosNum(@PathVariable("num") Integer num) {
		return new ResponseEntity<>(this.atrasoService.buscarAtrasosNum(num), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/analisarAtraso", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<HashMap<Object, Object>> analisarAtraso() {
		return new ResponseEntity<>(this.atrasoService.analisarAtraso(), HttpStatus.OK);
	}

}
