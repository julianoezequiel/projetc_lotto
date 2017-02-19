package br.com.lotto.rest;

import br.com.lotto.dto.Jogos;
import br.com.lotto.dto.JogosDTO;
import br.com.lotto.dto.MegaSenaDTO;
import br.com.lotto.dto.RespostaValidacao;
import br.com.lotto.dto.AtrazoDTO;
import br.com.lotto.dto.Configuracoes;
import br.com.lotto.dto.FrequenciaDTO;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.lotto.service.MegaSenaService;
import br.com.lotto.service.ServiceException;

@RestController
@RequestMapping(value = "/rest/megasena")
public class MegaSenaRestController {
	
	@Autowired
	private MegaSenaService mgaSenaService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<MegaSenaDTO>> listar() {
		return new ResponseEntity<>(mgaSenaService.buscartodos(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<MegaSenaDTO> buscarPorId(@PathVariable Integer id) throws ServiceException {
		return new ResponseEntity<>(mgaSenaService.buscarPorId(id), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/frequencia", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<FrequenciaDTO>> buscarFrequencias() {
		return new ResponseEntity<>(mgaSenaService.buscarFrequencias(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/atrazo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<AtrazoDTO> > buscarAtrazos() {
		return new ResponseEntity<>(mgaSenaService.buscarAtrazos(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/validarFrequencia", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<RespostaValidacao>> teste(@RequestBody JogosDTO jogosDTO) {
		return new ResponseEntity<>(mgaSenaService.validarFrequencia(jogosDTO.getJogos(),jogosDTO.getConfiguracoes()), HttpStatus.OK);
	}

}
