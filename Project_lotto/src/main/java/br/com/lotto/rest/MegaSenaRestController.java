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

import br.com.lotto.dto.AtrazoDTO;
import br.com.lotto.dto.Configuracoes;
import br.com.lotto.dto.FrequenciaDTO;
import br.com.lotto.dto.MegaSenaDTO;
import br.com.lotto.service.MegaSenaResultadoSimples;
import br.com.lotto.service.MegaSenaService;
import br.com.lotto.service.ServiceException;

@RestController
@RequestMapping(value = "/rest/megasena")
public class MegaSenaRestController {

	@Autowired
	private MegaSenaService megaSenaService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<MegaSenaDTO>> listar() {
		return new ResponseEntity<>(megaSenaService.buscartodos(), HttpStatus.OK);
	}

	@RequestMapping(value = "simples", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<MegaSenaResultadoSimples>> listarSimples() {
		return new ResponseEntity<>(megaSenaService.buscartodosSimples(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<MegaSenaDTO> buscarPorId(@PathVariable Integer id) throws ServiceException {
		return new ResponseEntity<>(megaSenaService.buscarPorId(id), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/frequencia", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<FrequenciaDTO>> buscarFrequencias() {
		return new ResponseEntity<>(megaSenaService.buscarFrequencias(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/atrazo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<AtrazoDTO>> buscarAtrazos() {
		return new ResponseEntity<>(megaSenaService.buscarAtrazos(), HttpStatus.OK);
	}

	/**
	 * Metodo para realizar a analise recursiva de frequencia dos numeros
	 * sorteados, ojetivo sera conseguir uma configuracao mais apropriada para
	 * utilizar na validacao. A cada sorteio realizado, devera verrificar qual
	 * numero mais e menos frequante foram sorteados
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/analizarFrequencia", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<HashMap<Long, Configuracoes>> analizar() {
		return new ResponseEntity<>(this.megaSenaService.iniciarAnalise(), HttpStatus.OK);
	}

}
