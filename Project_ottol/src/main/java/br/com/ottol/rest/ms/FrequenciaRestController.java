package br.com.ottol.rest.ms;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ottol.dto.FrequenciaDTO;
import br.com.ottol.service.ms.frequencia.FrequenciaService;

@RestController
@RequestMapping(value = "/rest/ms/frequencia")
public class FrequenciaRestController {

	@Autowired
	private FrequenciaService frequenciaService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<FrequenciaDTO>> buscarFrequencias() {
		return new ResponseEntity<>(this.frequenciaService.buscarFrequencias(), HttpStatus.OK);
	}

	/**
	 * Metodo para realizar a analise recursiva de frequencia dos numeros
	 * sorteados, ojetivo sera conseguir uma configuracao mais apropriada para
	 * utilizar na validacao. A cada sorteio realizado, devera verificar qual
	 * numero mais e menos frequante foram sorteados
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/analisarFrequencia", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<HashMap<Object, Object>> analisarFrequencia() {
		return new ResponseEntity<>(this.frequenciaService.analisarFrequencia(), HttpStatus.OK);
	}
}
