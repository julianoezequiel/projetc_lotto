package br.com.ottol.rest.ms;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ottol.dto.ConfiguracoesDTO;
import br.com.ottol.dto.MSDTO;
import br.com.ottol.dto.MegaSenaResultadoSimples;
import br.com.ottol.dto.Ppt;
import br.com.ottol.dto.RespostaValidacao;
import br.com.ottol.dto.ResultResumido;
import br.com.ottol.dto.ResultadoDTO;
import br.com.ottol.service.ServiceException;
import br.com.ottol.service.Validacao;
import br.com.ottol.service.ms.MSService;

@RestController
@RequestMapping(value = "/rest/ms")
public class MSRestController {

	@Autowired
	private MSService msService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<MSDTO>> listar() {
		return new ResponseEntity<>(this.msService.buscartodos(), HttpStatus.OK);
	}

	@RequestMapping(value = "resumido", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<MegaSenaResultadoSimples>> listarSimples() {
		return new ResponseEntity<>(this.msService.buscartodosSimples(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "ultimo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<MSDTO> buscarUltimo() throws ServiceException {
		return new ResponseEntity<>(this.msService.buscarUltimo(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "sincronizar", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> sincronizar() throws ServiceException {
		this.msService.sincronizar();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<MSDTO> buscarPorId(@PathVariable Integer id) throws ServiceException {
		return new ResponseEntity<>(this.msService.buscarPorId(id), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "carregar-listas", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<MSDTO> carregarListas() throws ServiceException {
		this.msService.carregarListas();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "validar", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<RespostaValidacao>> carregarListas(@RequestBody Ppt ppt) throws ServiceException {
		return new ResponseEntity<>(this.msService.validar(ppt), HttpStatus.OK);
	}

	@GetMapping(value = "gerar/{qtd}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ResultadoDTO>> gerar(@PathVariable Integer qtd) throws ServiceException {
		return new ResponseEntity<>(this.msService.gerar(qtd), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "gerar/{qtd}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<ResultadoDTO>> gerar2(@RequestBody Ppt ppt,@PathVariable Integer qtd) throws ServiceException {
		return new ResponseEntity<>(this.msService.gerar(ppt,qtd), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "validar-recursivo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<ResultResumido>> validarRecursivo(@RequestBody Ppt ppt) throws ServiceException {
		return new ResponseEntity<>(this.msService.validarRecursivo(ppt), HttpStatus.OK);
	}
}
