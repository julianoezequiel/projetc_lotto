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
import br.com.ottol.dto.ConfiguracoesDTO;
import br.com.ottol.dto.FrequenciaDTO;
import br.com.ottol.dto.MSDTO;
import br.com.ottol.dto.MegaSenaResultadoSimples;
import br.com.ottol.service.ServiceException;
import br.com.ottol.service.ms.MSService;

@RestController
@RequestMapping(value = "/rest/ms")
public class MSRestController {

	@Autowired
	private MSService msService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<MSDTO>> listar() {
		return new ResponseEntity<>(msService.buscartodos(), HttpStatus.OK);
	}

	@RequestMapping(value = "simples", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<MegaSenaResultadoSimples>> listarSimples() {
		return new ResponseEntity<>(msService.buscartodosSimples(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<MSDTO> buscarPorId(@PathVariable Integer id) throws ServiceException {
		return new ResponseEntity<>(msService.buscarPorId(id), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/frequencia", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<FrequenciaDTO>> buscarFrequencias() {
		return new ResponseEntity<>(msService.buscarFrequencias(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/atraso", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<AtrasoDTO>> buscarAtrazos() {
		return new ResponseEntity<>(msService.buscarAtrazos(), HttpStatus.OK);
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
	public ResponseEntity<HashMap<Long, ConfiguracoesDTO>> analisar() {
		return new ResponseEntity<>(this.msService.analisarFrequencia(), HttpStatus.OK);
	}

}
