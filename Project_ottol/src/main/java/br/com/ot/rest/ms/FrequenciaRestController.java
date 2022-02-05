package br.com.ot.rest.ms;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ot.dto.FrequenciaDTO;
import br.com.ot.service.ms.frequ.FrequenciaService;

@RestController
@RequestMapping(value = "/rest/ms/frequencia")
public class FrequenciaRestController {

	@Autowired
	private FrequenciaService frequenciaService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<FrequenciaDTO>> buscarFrequencias() {
		return new ResponseEntity<>(this.frequenciaService.buscarFrequencias(), HttpStatus.OK);
	}

	/**
	 * Metodo para realizar a analise recursiva de frequencia dos numeros
	 * sorteados, ojetivo sera conseguir uma configuracao mais apropriada para
	 * utilizar na validacao. A cada sorteio realizado, devera verificar qual
	 * num mais e menos frequante foram sorteados
	 * 
	 * @return
	 */
	@GetMapping(value = "/analisarFrequencia", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<Object, Object>> analisarFrequencia() {
		return new ResponseEntity<>(this.frequenciaService.analisarFrequencia(), HttpStatus.OK);
	}
}
