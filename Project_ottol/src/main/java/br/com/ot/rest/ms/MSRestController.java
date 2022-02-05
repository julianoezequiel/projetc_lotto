package br.com.ot.rest.ms;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ot.dto.MSDTO;
import br.com.ot.dto.MSResultadoSimples;
import br.com.ot.dto.Ppt;
import br.com.ot.dto.RespostaValidacao;
import br.com.ot.dto.ResultResumido;
import br.com.ot.dto.ResultadoDTO;
import br.com.ot.service.ServiceException;
import br.com.ot.service.ms.MSService;

@RestController
@RequestMapping(value = "/rest/ms")
public class MSRestController {

	@Autowired
	private MSService msService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<MSDTO>> listar() {
		return new ResponseEntity<>(this.msService.buscartodos(), HttpStatus.OK);
	}

	@GetMapping(value = "resumido", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<MSResultadoSimples>> listarSimples() {
		return new ResponseEntity<>(this.msService.buscartodosSimples(), HttpStatus.OK);
	}

	@GetMapping(value = "ultimo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MSDTO> buscarUltimo() throws ServiceException {
		return new ResponseEntity<>(this.msService.buscarUltimo(), HttpStatus.OK);
	}

	@GetMapping(value = "sincronizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> sincronizar() throws ServiceException {
		this.msService.sincronizar();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MSDTO> buscarPorId(@PathVariable Integer id) throws ServiceException {
		return new ResponseEntity<>(this.msService.buscarPorId(id), HttpStatus.OK);
	}

	@GetMapping(value = "carregar-listas", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MSDTO> carregarListas() throws ServiceException {
		this.msService.carregarListas();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(value = "validar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RespostaValidacao>> carregarListas(@RequestBody Ppt ppt) throws ServiceException {
		return new ResponseEntity<>(this.msService.validar(ppt), HttpStatus.OK);
	}

	@GetMapping(value = "gerar/{qtd}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ResultadoDTO>> gerar(@PathVariable Integer qtd) throws ServiceException {
		return new ResponseEntity<>(this.msService.gerar(qtd), HttpStatus.OK);
	}

	@PostMapping(value = "gerar/{qtd}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ResultadoDTO>> gerar2(@RequestBody Ppt ppt,@PathVariable Integer qtd) throws ServiceException {
		return new ResponseEntity<>(this.msService.gerar(ppt,qtd), HttpStatus.OK);
	}

	@PostMapping(value = "validar-recursivo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ResultResumido>> validarRecursivo(@RequestBody Ppt ppt) throws ServiceException {
		return new ResponseEntity<>(this.msService.validarRecursivo(ppt), HttpStatus.OK);
	}
}
