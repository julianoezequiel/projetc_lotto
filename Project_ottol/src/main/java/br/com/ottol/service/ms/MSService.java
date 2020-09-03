/**
 *
 */
package br.com.ottol.service.ms;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ottol.dao.MSRepository;
import br.com.ottol.dto.ConfiguracoesDTO;
import br.com.ottol.dto.Jogos;
import br.com.ottol.dto.MSDTO;
import br.com.ottol.dto.MegaSenaResultadoSimples;
import br.com.ottol.dto.NumeroDTO;
import br.com.ottol.dto.PalpiteDTO;
import br.com.ottol.dto.RespostaValidacao;
import br.com.ottol.dto.ResultResumido;
import br.com.ottol.dto.ResultadoDTO;
import br.com.ottol.entity.MS;
import br.com.ottol.entity.Numero;
import br.com.ottol.service.RestTemplateProxy;
import br.com.ottol.service.ServiceException;
import br.com.ottol.service.Validacao;
import br.com.ottol.service.ms.comb.CombinacoesServices;
import br.com.ottol.service.ms.comb.validacao.ListaA;
import br.com.ottol.service.ms.comb.validacao.ListaB;
import br.com.ottol.service.ms.comb.validacao.ListaC;
import br.com.ottol.service.ms.comb.validacao.ListaD;
import br.com.ottol.service.ms.comb.validacao.ListaE;
import br.com.ottol.service.ms.dto.MSSincronizarDTO;
import br.com.ottol.service.ms.frequ.FrequenciaService;

/**
 * @author Juliano
 *
 */
@Service
public class MSService {

	public final static Logger LOGGER = LoggerFactory.getLogger(MSService.class.getName());

	@Autowired
	private MSRepository msRepository;
	@Autowired
	private FrequenciaService frequenciaService;
	@Autowired
	private CombinacoesServices combinacoesServices;
	@Autowired
	private RestTemplateProxy restTemplateProxy;
	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private Gerador gerador;

	/**
	 * Lista todos os concursos
	 *
	 * @return
	 */
	public Collection<MSDTO> buscartodos() {
		Collection<MSDTO> list = new ArrayList<>();
		this.msRepository.findAll().stream().forEach(s -> {
			list.add(s.toMegaSenaDTO());
		});
		return list;
	}

	private String sorteados;

	/**
	 * Lista todos os concursos resultados Simples
	 *
	 * @return
	 */
	public Collection<MegaSenaResultadoSimples> buscartodosSimples() {
		Collection<MegaSenaResultadoSimples> list = new ArrayList<>();
		this.msRepository.findAll().stream().forEach(s -> {
			sorteados = "";
			s.getMegasenanumeroCollection().stream().forEach(n -> {
				sorteados = sorteados + n.getNumero().getDescricao() + " - ";
			});
			sorteados = sorteados.substring(0, sorteados.lastIndexOf(" -"));
			list.add(new MegaSenaResultadoSimples(s.getConcurso(), sorteados));
		});
		return list;
	}

	/**
	 * Lista todos os concursos
	 *
	 * @return
	 */
	public Collection<Jogos> buscartodosConcursos() {
		List<Jogos> list = new ArrayList<>();

		this.msRepository.findAll().stream().forEach(s -> {
			list.add(new Jogos(s.getConcurso(), s.getMegasenanumeroCollection()));
		});
		return list;
	}

	public Collection<Jogos> buscarMenorQueConcursos(Long id) {
		List<Jogos> list = new ArrayList<>();

		this.msRepository.buscarMenorQue(id.intValue()).stream().forEach(s -> {
			list.add(new Jogos(s.getConcurso(), s.getMegasenanumeroCollection()));
		});
		return list;
	}

	/**
	 * Busca o concurso pelo id do concuso
	 *
	 * @param id
	 * @return
	 */
	public MSDTO buscarPorId(Integer id) throws ServiceException {
		Optional<MS> mS = this.msRepository.findById(id);
		if (mS.isPresent()) {
			return mS.get().toMegaSenaDTO();
		} else {
			throw new ServiceException(HttpStatus.NO_CONTENT, "Concurso não encontrado");
		}
	}

	public MSDTO buscarUltimo() throws ServiceException {
		MS mS = this.msRepository.getUltimoConcurso();
		if (mS != null) {
			return mS.toMegaSenaDTO();
		} else {
			throw new ServiceException(HttpStatus.NO_CONTENT, "Concurso não encontrado");
		}
	}

	ConfiguracoesDTO configuracoes = new ConfiguracoesDTO();

	public ConfiguracoesDTO getConfigDefault() {

		// configuracoes.setMaisAtrazado(getnumRand());
		this.configuracoes.setMaisFrequente(
				this.configuracoes.getMaisFrequente() <= 60 && this.configuracoes.getMenosFrequente() == 60
						? this.configuracoes.getMaisFrequente() + 1
						: this.configuracoes.getMaisFrequente());
		// configuracoes.setMenosAtrazado(getnumRand());
		this.configuracoes.setMenosFrequente(
				this.configuracoes.getMenosFrequente() < 60 ? this.configuracoes.getMenosFrequente() + 1 : 1);
		return configuracoes;
	}

	Random rand = new Random();

	private int getnumRand() {
		return ThreadLocalRandom.current().nextInt(1, 60);
	}

	public void verificarListas() {

//		List<MS> list = this.msRepository.findAll();
//
//		this.combinacoesServices.carregarLista(list);
//
//		List<JGDerivadoValidacao> listaA = ListaA.LISTA_A;
//		List<JGDerivadoValidacao> listaB = ListaB.LISTA_B;
//		List<JGDerivadoValidacao> listaC = ListaC.LISTA_C;
//		List<JGDerivadoValidacao> listaD = ListaD.LISTA_D;

		HashMap<Object, Object> map = this.combinacoesServices.analiseCombinacoes();

		System.out.println("Carregado");

	}

	public long total() {
		return this.msRepository.count();
	}

//	@PostConstruct
	public void sincronizar() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
		headers.add("Accept", "application/json");
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

		HttpEntity<String> httpEntity = new HttpEntity<>("parameters", headers);
		RestTemplate restTemplateAuth = this.restTemplateProxy.restTemplate();
		String url = "https://confiraloterias.com.br/api/json/?loteria=megasena&lista=true&token=6aSWlYrZz0H64s4";
		// envia a solicitacao
		ResponseEntity<JsonNode> responseAuth = restTemplateAuth.exchange(URI.create(url), HttpMethod.GET, httpEntity,
				JsonNode.class);

		HttpHeaders httpHeaders = responseAuth.getHeaders();
		HttpStatus statusCode = responseAuth.getStatusCode();

		if (statusCode.equals(HttpStatus.MOVED_PERMANENTLY) || statusCode.equals(HttpStatus.FOUND)
				|| statusCode.equals(HttpStatus.SEE_OTHER)) {
			if (httpHeaders.getLocation() != null) {
				String saida = perFormRestCall(httpHeaders.getLocation().toString());
				LOGGER.debug(saida);
			} else {
				throw new RuntimeException();
			}
		} else if (statusCode.equals(HttpStatus.OK)) {
			JsonNode saida = responseAuth.getBody();
			int i = saida.size();
			LOGGER.debug(saida.toString());
		}
	}

	private String perFormRestCall(String url) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
			headers.add("Accept", "application/json");
			headers.add("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

			HttpEntity<String> httpEntity = new HttpEntity<>("parameters", headers);
			RestTemplate restTemplateAuth = new RestTemplate();
			ResponseEntity<String> responseAuth = restTemplateAuth.exchange(URI.create(url), HttpMethod.GET, httpEntity,
					String.class);

			return responseAuth.getBody();
		} catch (Exception ex) {
			throw new RuntimeException();
		}
	}

	public void carregarListas() {
		this.verificarListas();

	}

	public List<RespostaValidacao> validar(PalpiteDTO palpiteDTO) {
		Integer id = palpiteDTO.getMegasenaidconcurso().getIdconcurso();
		this.combinacoesServices.limparListas();
		Collection<MS> list = this.msRepository.buscarMenorQue(id-1);
		this.combinacoesServices.carregarLista(new ArrayList<MS>(list));
		List<RespostaValidacao> validar = this.combinacoesServices.validar(palpiteDTO);
		ResultResumido r = new ResultResumido(id, validar);
		LOGGER.debug("VALIDADO :{}", r.toString());
		return validar;
	}

	public List<ResultResumido> validarRecursivo(PalpiteDTO palpiteDTO) {
		List<ResultResumido> result = new ArrayList<>();
		MS ultimo = this.msRepository.getUltimoConcurso();
		List<MS> todos = this.msRepository.findAll();
		for (Integer i = palpiteDTO.getMegasenaidconcurso().getIdconcurso(); i < ultimo.getIdconcurso(); i++) {
			final int ii = i;
			this.combinacoesServices.limparListas();
			List<MS> menor = todos.stream().filter(p -> p.getIdconcurso() <= ii).collect(Collectors.toList());
			this.combinacoesServices.carregarLista(new ArrayList<MS>(menor));
			PalpiteDTO p = new PalpiteDTO();
			p.getConfiguracoesCollection().add(new ConfiguracoesDTO());
			p.setMegasenaidconcurso(new MS(i));
			MS proximo = todos.stream().filter(pp -> pp.getIdconcurso() == ii + 1).findAny().orElse(null);
			if (proximo != null) {
				List<NumeroDTO> list = proximo.getMegasenanumeroCollection().stream()
						.map(m -> new NumeroDTO(m.getNumero().getIdnumero())).collect(Collectors.toList());
				p.setNumeroCollection(list);
				List<RespostaValidacao> validar = this.combinacoesServices.validar(palpiteDTO);
				ResultResumido r = new ResultResumido(i, validar);
				result.add(r);
				LOGGER.debug("VALIDADO :{}", r.toString());
			}
		}
		return result;
	}

	public List<ResultadoDTO> gerar(Integer qtd) {
		List<ResultadoDTO> result = new ArrayList<>();
		List<NumeroDTO> listTeste = getListTeste();
		try {
			HashMap<Object, Object> map = this.combinacoesServices.analiseCombinacoes();
			Integer tentativa = 0;
			Integer ap = 0;
			for (int ix = 1; ix <= qtd; ix++) {

				PalpiteDTO ppt = new PalpiteDTO();
				ConfiguracoesDTO conf = new ConfiguracoesDTO();
				ppt.getConfiguracoesCollection().add(conf);
				MS ms = new MS(1);
				ppt.setMegasenaidconcurso(ms);
				Integer[] valor2 = this.gerador.Valor2();
				List<NumeroDTO> nList = new ArrayList<>();
				for (Integer i : valor2) {
					nList.add(new NumeroDTO(i));
				}

				ppt.setNumeroCollection(nList);
				List<RespostaValidacao> validar = this.combinacoesServices.validar(ppt);
				if (validar.stream().anyMatch(p -> !p.getAprovado())) {
					ix--;
				} else {
					ap++;
					AtomicInteger v = new AtomicInteger(0);
					listTeste.stream().forEach(f -> {
						nList.stream().forEach(ff -> {
							if (f.getIdNumero().equals(ff.getIdNumero())) {
								v.getAndIncrement();
							}
						});
					});
					if (v.get() >= 4) {
						result.add(new ResultadoDTO(ppt, validar));
					} else {
						ix--;
					}
				}
				tentativa++;
				LOGGER.debug("T:{} - A:{} - V:{}", tentativa, ap, ix);
//				LOGGER.debug("Result: {}", validar);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return result;
	}

	private List<NumeroDTO> getListTeste() {
		List<NumeroDTO> nList = new ArrayList<>();
		nList.add(new NumeroDTO(8));
		nList.add(new NumeroDTO(11));
		nList.add(new NumeroDTO(27));
		nList.add(new NumeroDTO(28));
		nList.add(new NumeroDTO(43));
		nList.add(new NumeroDTO(46));
		return nList;
	}

	// 8 11 27 28 43 46
	// 11 12 26 30 37 53
	//

}
