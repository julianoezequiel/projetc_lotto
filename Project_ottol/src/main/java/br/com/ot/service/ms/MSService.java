/**
 *
 */
package br.com.ot.service.ms;

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

import br.com.ot.dao.MSRepository;
import br.com.ot.dto.AtrasoDTO;
import br.com.ot.dto.ConfiguracoesDTO;
import br.com.ot.dto.FrequenciaDTO;
import br.com.ot.dto.Jgs;
import br.com.ot.dto.MSDTO;
import br.com.ot.dto.MSResultadoSimples;
import br.com.ot.dto.NumeroDTO;
import br.com.ot.dto.Ppt;
import br.com.ot.dto.RespostaValidacao;
import br.com.ot.dto.ResultResumido;
import br.com.ot.dto.ResultadoDTO;
import br.com.ot.entity.MS;
import br.com.ot.entity.Numero;
import br.com.ot.service.RestTemplateProxy;
import br.com.ot.service.ServiceException;
import br.com.ot.service.Validacao;
import br.com.ot.service.ms.atraso.AtrasoService;
import br.com.ot.service.ms.comb.CombinacoesServices;
import br.com.ot.service.ms.comb.validacao.ListaA;
import br.com.ot.service.ms.comb.validacao.ListaB;
import br.com.ot.service.ms.comb.validacao.ListaC;
import br.com.ot.service.ms.comb.validacao.ListaD;
import br.com.ot.service.ms.comb.validacao.ListaE;
import br.com.ot.service.ms.dto.MSSincronizarDTO;
import br.com.ot.service.ms.frequ.FrequenciaService;
import br.com.ot.service.num.NumeroService;

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
	@Autowired
	private AtrasoService atrasoService;
	@Autowired
	private NumeroService numeroService;

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
	public Collection<MSResultadoSimples> buscartodosSimples() {
		Collection<MSResultadoSimples> list = new ArrayList<>();
		this.msRepository.findAll().stream().forEach(s -> {
			sorteados = "";
			s.getMegasenanumeroCollection().stream().forEach(n -> {
				sorteados = sorteados + n.getNumero().getDescricao() + " - ";
			});
			sorteados = sorteados.substring(0, sorteados.lastIndexOf(" -"));
			list.add(new MSResultadoSimples(s.getConcurso(), sorteados));
		});
		return list;
	}

	/**
	 * Lista todos os concursos
	 *
	 * @return
	 */
	public Collection<Jgs> buscartodosConcursos() {
		List<Jgs> list = new ArrayList<>();

		this.msRepository.findAll().stream().forEach(s -> {
			list.add(new Jgs(s.getConcurso(), s.getMegasenanumeroCollection()));
		});
		return list;
	}

	public Collection<Jgs> buscarMenorQueConcursos(Long id) {
		List<Jgs> list = new ArrayList<>();

		this.msRepository.buscarMenorQue(id.intValue()).stream().forEach(s -> {
			list.add(new Jgs(s.getConcurso(), s.getMegasenanumeroCollection()));
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

		Map<Object, Object> map = this.combinacoesServices.analiseCombinacoes();

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

	public List<RespostaValidacao> validar(Ppt ppt) {
		ppt.corrigirPpt();
		Integer id = ppt.getMegasenaidconcurso().getIdconcurso();
		Collection<MS> menorQue = this.msRepository.buscarMenorQue(id);
		Map<String, List<JGDerivadoValidacao>> map = this.combinacoesServices.carregarLista(new ArrayList<>(menorQue));
		Collection<Numero> nums = this.numeroService.buscarTodos();
		Collection<AtrasoDTO> atrazados = this.atrasoService.buscarAtrasos(this.total(), 0, nums);
		Collection<MS> list = this.msRepository.buscarMenorQue(id - 1);
		this.combinacoesServices.carregarLista(new ArrayList<MS>(list));
		List<RespostaValidacao> validar = this.combinacoesServices.validar(ppt, map);
		validar.addAll(this.atrasoService.validar(ppt, atrazados));
		ResultResumido r = new ResultResumido(id - 1, validar);
		LOGGER.debug("VALIDADO :{}", r.toString());
		return validar;
	}

	// valida de forma recursiva se as regras implementadas estão com um percentual
	// de acerto bom
	public List<ResultResumido> validarRecursivo(Ppt ppt) {

		List<ResultResumido> result = new ArrayList<>();
		MS ultimo = this.msRepository.getUltimoConcurso();
		Collection<Numero> nums = this.numeroService.buscarTodos();
		ppt.corrigirPpt();
		Collection<MS> todos = this.msRepository.buscarMaiorQue(ppt.getMegasenaidconcurso().getIdconcurso());

//		this.frequenciaService.analiseRecursiva(ppt);

		Collection<AtrasoDTO> atrasoRecursivo = this.atrasoService.analizarRecursivo(ppt);
		Integer primeiro = ppt.getMegasenaidconcurso().getIdconcurso();
		todos.stream().forEach(f -> {
			final int ii = f.getIdconcurso();

			List<MS> menor = todos.stream().filter(p -> p.getIdconcurso() <= ii).collect(Collectors.toList());

			Map<String, List<JGDerivadoValidacao>> map2 = this.combinacoesServices
					.carregarLista(new ArrayList<MS>(menor));

			Collection<AtrasoDTO> atrazados = this.atrasoService.buscarAtrasos((long) ii, 0, nums);

			Ppt p = ppt;
			p.setMegasenaidconcurso(f);

			MS proximo = todos.stream().filter(pp -> pp.getIdconcurso() == ii + 1).findAny().orElse(null);
			if (proximo != null) {
				List<NumeroDTO> list = proximo.getMegasenanumeroCollection().stream()
						.map(m -> new NumeroDTO(m.getNumero().getIdnumero())).collect(Collectors.toList());
				p.setNumeroCollection(list);
				Collection<FrequenciaDTO> frequencias = this.frequenciaService.buscarFrequencias(ppt);
				List<RespostaValidacao> validar = validar(atrasoRecursivo, atrazados, p, map2, frequencias);

				ResultResumido r = new ResultResumido(f.getIdconcurso(), validar);
				result.add(r);
				LOGGER.debug("V :{}", r);
			}
		});
//		for (Integer i = ppt.getMegasenaidconcurso().getIdconcurso(); i < ultimo.getIdconcurso(); i++) {
//			final int ii = i;
//			this.combinacoesServices.limparListas();
//			List<MS> menor = todos.stream().filter(p -> p.getIdconcurso() <= ii).collect(Collectors.toList());
//
//			this.combinacoesServices.carregarLista(new ArrayList<MS>(menor));
//			Collection<AtrasoDTO> atrazados = this.atrasoService.buscarAtrasos((long) ii, 0, numeros);
//
//			Ppt p = ppt;
//			p.setMegasenaidconcurso(new MS(i));
//
//			MS proximo = todos.stream().filter(pp -> pp.getIdconcurso() == ii + 1).findAny().orElse(null);
//			if (proximo != null) {
//				List<NumeroDTO> list = proximo.getMegasenanumeroCollection().stream()
//						.map(m -> new NumeroDTO(m.getNumero().getIdnumero())).collect(Collectors.toList());
//				p.setNumeroCollection(list);
//
//				List<RespostaValidacao> validar = validar(atrasoRecursivo, atrazados, p);
//
//				ResultResumido r = new ResultResumido(i, validar);
//				result.add(r);
//				LOGGER.debug("VALIDADO :{}", r);
//			}
//		}
		Map<Integer, Long> countA = result.stream()
				.collect(Collectors.groupingBy(ResultResumido::getFreqlistaA, Collectors.counting()));
		Map<Integer, Long> countB = result.stream()
				.collect(Collectors.groupingBy(ResultResumido::getFreqlistaB, Collectors.counting()));
		Map<Integer, Long> countC = result.stream()
				.collect(Collectors.groupingBy(ResultResumido::getFreqlistaC, Collectors.counting()));
		Map<Integer, Long> countD = result.stream()
				.collect(Collectors.groupingBy(ResultResumido::getFreqlistaD, Collectors.counting()));
		Map<Integer, Long> countE = result.stream()
				.collect(Collectors.groupingBy(ResultResumido::getFreqlistaE, Collectors.counting()));
		Map<Integer, Long> countVMA = result.stream()
				.collect(Collectors.groupingBy(ResultResumido::getMaisAtrazado, Collectors.counting()));
		Map<Integer, Long> countVMEA = result.stream()
				.collect(Collectors.groupingBy(ResultResumido::getMenosAtrasado, Collectors.counting()));
		Map<Integer, Long> countCiclo = result.stream()
				.collect(Collectors.groupingBy(ResultResumido::getMediaCiclo, Collectors.counting()));

		Map<Integer, Long> countVMF = result.stream()
				.collect(Collectors.groupingBy(ResultResumido::getMaisFrequente, Collectors.counting()));
		Map<Integer, Long> countVMEF = result.stream()
				.collect(Collectors.groupingBy(ResultResumido::getMenosFreuqente, Collectors.counting()));

		Map<Boolean, Long> countAP = result.stream()
				.collect(Collectors.groupingBy(ResultResumido::getAprovado, Collectors.counting()));
		ArrayList<String> resumo = new ArrayList<>();
		Double divisor = Double.valueOf(ultimo.getIdconcurso() - primeiro);

		countA.entrySet().forEach(f -> {
			LOGGER.debug("count A - {} - T:{} - {}%", f.getKey(), f.getValue(), f.getValue() * 100 / divisor);
			resumo.add("count A - " + f.getKey() + " - T:" + f.getValue() + " - " + f.getValue() * 100 / divisor + "%");

		});
		countB.entrySet().forEach(f -> {
			LOGGER.debug("count B - {} - T:{} - {}%", f.getKey(), f.getValue(), f.getValue() * 100l / divisor);
			resumo.add("count B - " + f.getKey() + " - T:" + f.getValue() + " - " + f.getValue() * 100 / divisor + "%");
		});
		countC.entrySet().forEach(f -> {
			LOGGER.debug("count C - {} - T:{} - {}%", f.getKey(), f.getValue(), f.getValue() * 100l / divisor);
			resumo.add("count C - " + f.getKey() + " - T:" + f.getValue() + " - " + f.getValue() * 100 / divisor + "%");
		});
		countD.entrySet().forEach(f -> {
			LOGGER.debug("count D - {} - T:{} - {}%", f.getKey(), f.getValue(), f.getValue() * 100l / divisor);
			resumo.add("count D - " + f.getKey() + " - T:" + f.getValue() + " - " + f.getValue() * 100 / divisor + "%");
		});
		countE.entrySet().forEach(f -> {
			LOGGER.debug("count E - {} - T:{} - {}%", f.getKey(), f.getValue(), f.getValue() * 100l / divisor);
			resumo.add("count E - " + f.getKey() + " - T:" + f.getValue() + " - " + f.getValue() * 100 / divisor + "%");
		});
		countVMA.entrySet().forEach(f -> {
			LOGGER.debug("count VMA - {} - T:{} - {}%", f.getKey(), f.getValue(), f.getValue() * 100l / divisor);
			resumo.add(
					"count VMA - " + f.getKey() + " - T:" + f.getValue() + " - " + f.getValue() * 100 / divisor + "%");
		});
		countVMEA.entrySet().forEach(f -> {
			LOGGER.debug("count VMEA - {} - T:{} - {}%", f.getKey(), f.getValue(), f.getValue() * 100l / divisor);
			resumo.add(
					"count VMEA - " + f.getKey() + " - T:" + f.getValue() + " - " + f.getValue() * 100 / divisor + "%");
		});
		countCiclo.entrySet().forEach(f -> {
			LOGGER.debug("count CIC - {} - T:{} - {}%", f.getKey(), f.getValue(), f.getValue() * 100l / divisor);
			resumo.add(
					"count CIC - " + f.getKey() + " - T:" + f.getValue() + " - " + f.getValue() * 100 / divisor + "%");
		});

		countVMF.entrySet().forEach(f -> {
			LOGGER.debug("count VMF - {} - T:{} - {}%", f.getKey(), f.getValue(), f.getValue() * 100l / divisor);
			resumo.add(
					"count VMF - " + f.getKey() + " - T:" + f.getValue() + " - " + f.getValue() * 100 / divisor + "%");
		});
		countVMEF.entrySet().forEach(f -> {
			LOGGER.debug("count VMEF - {} - T:{} - {}%", f.getKey(), f.getValue(), f.getValue() * 100l / divisor);
			resumo.add(
					"count VMEF - " + f.getKey() + " - T:" + f.getValue() + " - " + f.getValue() * 100 / divisor + "%");
		});

		countAP.entrySet().forEach(f -> {
			LOGGER.debug("count AP - {} - T:{} - {}%", f.getKey(), f.getValue(), f.getValue() * 100l / divisor);
			resumo.add(
					"count AP - " + f.getKey() + " - T:" + f.getValue() + " - " + f.getValue() * 100 / divisor + "%");
		});
		result.get(result.size() - 1).setResumo(resumo);
		return result;
	}

	private List<RespostaValidacao> validar(Collection<AtrasoDTO> atrasoRecursivo, Collection<AtrasoDTO> atrazados,
			Ppt p, Map<String, List<JGDerivadoValidacao>> map, Collection<FrequenciaDTO> frequencias) {
		List<RespostaValidacao> validar = this.combinacoesServices.validar(p, map);
		validar.addAll(this.atrasoService.validar(p, atrazados));
		validar.add(this.atrasoService.validarCiclo(p, atrasoRecursivo));
		validar.add(this.frequenciaService.validarMaisFrequente(p, frequencias));
		validar.add(this.frequenciaService.validarMenosFrequente(p, frequencias));
		return validar;
	}

	public List<ResultadoDTO> gerar(Integer qtd) {
		List<ResultadoDTO> result = new ArrayList<>();
		List<NumeroDTO> listTeste = getListTeste();
		try {
//			Map<Object, Object> map = this.combinacoesServices.analiseCombinacoes();
			this.atrasoService.analisarAtraso();
			Integer tentativa = 0;
			Integer ap = 0;
			Ppt ppt = new Ppt();
			ConfiguracoesDTO conf = new ConfiguracoesDTO();
			ppt.setConfiguracoesDTO(conf);
			MS ms = new MS(1);
			ppt.setMegasenaidconcurso(ms);

			Collection<MS> menorQue = this.msRepository.buscarMenorQue(1);
			Map<String, List<JGDerivadoValidacao>> map = this.combinacoesServices
					.carregarLista(new ArrayList<>(menorQue));

			Collection<Numero> nums = this.numeroService.buscarTodos();
			Collection<AtrasoDTO> atrazados = this.atrasoService.buscarAtrasos(this.total(), 0, nums);
			Collection<AtrasoDTO> atrasoRecursivo = this.atrasoService.analizarRecursivo(ppt);
			for (int ix = 1; ix <= qtd; ix++) {

				Integer[] valor2 = this.gerador.Valor2();
				List<NumeroDTO> nList = new ArrayList<>();
				for (Integer i : valor2) {
					nList.add(new NumeroDTO(i));
				}

				ppt.setNumeroCollection(nList);

				List<RespostaValidacao> validar = validar(atrasoRecursivo, atrazados, ppt, map, null);

				tentativa++;
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
					if (v.get() >= 5) {
						result.add(new ResultadoDTO(ppt, validar, tentativa, ap, ix));
					} else {
						ix--;
					}
				}

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
		nList.add(new NumeroDTO(11));
		nList.add(new NumeroDTO(12));
		nList.add(new NumeroDTO(26));
		nList.add(new NumeroDTO(30));
		nList.add(new NumeroDTO(37));
		nList.add(new NumeroDTO(53));
		return nList;
	}

	public List<ResultadoDTO> gerar(Ppt ppt, Integer qtd) {
		ppt.corrigirPpt();
		List<ResultadoDTO> result = new ArrayList<>();
		List<NumeroDTO> listTeste = getListTeste();
		try {
//			HashMap<Object, Object> map = this.combinacoesServices.analiseCombinacoes();
			this.atrasoService.analisarAtraso();
			Collection<AtrasoDTO> atrasoRecursivo = this.atrasoService.analizarRecursivo(ppt);
			Integer tentativa = 0;
			Integer ap = 0;
			Collection<Numero> nums = this.numeroService.buscarTodos();
			Collection<AtrasoDTO> atrazados = this.atrasoService.buscarAtrasos(this.total(), 0, nums);

			Collection<MS> menorQue = this.msRepository.buscarMenorQue(ppt.getMegasenaidconcurso().getIdconcurso() - 1);
			Map<String, List<JGDerivadoValidacao>> map = this.combinacoesServices
					.carregarLista(new ArrayList<>(menorQue));

			for (int ix = 1; ix <= qtd; ix++) {
				Integer[] valor2 = this.gerador.Valor2();
				List<NumeroDTO> nList = new ArrayList<>();
				for (Integer i : valor2) {
					nList.add(new NumeroDTO(i));
				}

				ppt.setNumeroCollection(nList);

				List<RespostaValidacao> validar = validar(atrasoRecursivo, atrazados, ppt, map, null);

				tentativa++;
//				validar.stream().forEach(f -> LOGGER.debug("V" + f.getValidacao() + " - " + f.getAprovado()));
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
					if (v.get() >= ppt.getLimiteAcerto()) {
						result.add(new ResultadoDTO(ppt, validar, tentativa, ap, ix));
					} else {
						ix--;
					}
				}

				LOGGER.debug("T:{} - A:{} - V:{}", tentativa, ap, ix);
//				LOGGER.debug("Result: {}", validar);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return result;
	}

	// 8 11 27 28 43 46
	// 11 12 26 30 37 53
	// 11 12 26 30 37 53

}
