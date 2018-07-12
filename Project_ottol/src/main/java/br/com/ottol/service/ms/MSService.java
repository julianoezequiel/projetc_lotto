/**
 *
 */
package br.com.ottol.service.ms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.ottol.dao.MSRepository;
import br.com.ottol.dto.AtrasoDTO;
import br.com.ottol.dto.ConfiguracoesDTO;
import br.com.ottol.dto.FrequenciaDTO;
import br.com.ottol.dto.Jogos;
import br.com.ottol.dto.MSDTO;
import br.com.ottol.dto.MegaSenaResultadoSimples;
import br.com.ottol.dto.RespostaValidacao;
import br.com.ottol.entity.MS;
import br.com.ottol.service.ServiceException;
import br.com.ottol.service.ms.atraso.analise.AnaliseAtraso;
import br.com.ottol.service.ms.combinacoes.CombinacoesServices;
import br.com.ottol.service.ms.combinacoes.analise.AnaliseCombinacoes;
import br.com.ottol.service.ms.combinacoes.validacao.ListaA;
import br.com.ottol.service.ms.combinacoes.validacao.ListaB;
import br.com.ottol.service.ms.combinacoes.validacao.ListaC;
import br.com.ottol.service.ms.combinacoes.validacao.ListaD;
import br.com.ottol.service.ms.frequencia.FrequenciaService;
import br.com.ottol.service.ms.frequencia.analise.AnaliseFrequencia;
import br.com.ottol.service.ms.frequencia.validacao.ValidacaoFrequente;
import br.com.ottol.utils.CONSTANTES.PARAM;

/**
 * @author Juliano
 *
 */
@Service
public class MSService {

	@Autowired
	private MSRepository msRepository;
	@Autowired
	private FrequenciaService frequenciaService;
	@Autowired
	private AnaliseAtraso analiseAtraso;
	@Autowired
	private AnaliseFrequencia analiseFrequencia;
	@Autowired
	private CombinacoesServices combinacoesServices;
	@Autowired
	private AnaliseCombinacoes analiseCombinacoes;

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
		MS mS = this.msRepository.findOne(id);
		if (mS != null) {
			return mS.toMegaSenaDTO();
		} else {
			throw new ServiceException(HttpStatus.NO_CONTENT, "Concurso não encontrado");
		}
	}

	/**
	 * Calcula quantas vezes cada dezena ja foi sorteada
	 *
	 * @return
	 */
	public Collection<FrequenciaDTO> buscarFrequencias() {
		return frequenciaService.buscarFrequencias();
	}

	/**
	 * Calcula os atrazos das dezenas noc concursos realizados
	 *
	 * @return
	 */
	public Collection<AtrasoDTO> buscarAtrasos(Integer maxConc) {
		return this.analiseAtraso.buscarAtrasos(maxConc.longValue(), 0);
	}

	public Collection<AtrasoDTO> buscarAtrazos() {
		return this.analiseAtraso.buscarAtrasos(this.total(), 0);
	}

	private HashMap<Long, ConfiguracoesDTO> melhorConfig = new HashMap<>();
	private int totalTentativas = 1000;
	private int passo = 0;
	private Long max = 0l;

	public HashMap<Long, ConfiguracoesDTO> iniciarAnalise() {
		max = 0l;
		totalTentativas = 1000;
		passo = 0;
		melhorConfig = new HashMap<>();

		return analisar();
	}

	private ConfiguracoesDTO config = new ConfiguracoesDTO();

	public HashMap<Long, ConfiguracoesDTO> analisarFrequencia() {
		HashMap<PARAM, Object> params = new HashMap<>();
		params.put(PARAM.PARAM_INICIO, 1);
		params.put(PARAM.PARAM_NUMERO, 0);
		this.analiseFrequencia.init(params);
		return melhorConfig;
	}

	public HashMap<Long, ConfiguracoesDTO> analisarAtraso() {
		HashMap<PARAM, Object> params = new HashMap<>();
		params.put(PARAM.PARAM_INICIO, 1);
		params.put(PARAM.PARAM_NUMERO, 0);
		this.analiseAtraso.init(params);
		return melhorConfig;
	}

	public HashMap<Long, ConfiguracoesDTO> analisar() {

		HashMap<PARAM, Object> params = new HashMap<>();
		params.put(PARAM.PARAM_INICIO, 1);
		params.put(PARAM.PARAM_NUMERO, 0);
		analiseFrequencia.init(params);

		// long totalJogos = this.megaSenaRepository.count();
		//
		// while (totalJogos > 0) {
		// config = getConfigDefault();
		// Collection<Jogos> jogos =
		// this.buscarMenorQueConcursos(config.getMaisFrequente());
		// Collection<RespostaValidacao> validacoes = new ArrayList<>();
		//
		// jogos.stream().forEach(jogo -> {
		// System.out.println("Jogo : " + jogo.toString());
		// validacoes.add(this.maisFrequenteValidacaoService.validar(config,
		// jogo));
		// validacoes.add(this.menosFrequenteValidacaoService.validar(config,
		// jogo));
		// });
		//
		// long subTotal = validacoes.stream().filter(f ->
		// f.getAprovado().equals(true)).count();
		//
		// if (subTotal > max) {
		//
		// max = subTotal;
		// melhorConfig.put(max, config.newIntance());
		// System.out.println(melhorConfig.values().toArray());
		// }
		//
		// totalJogos--;
		// System.out.println("Total de jogos : " + totalJogos);
		// }
		//
		// if (config.getMaisFrequente() != 60 || config.getMenosFrequente() !=
		// 60) {
		// passo++;
		// System.out.println("Teste : " + passo + " validação : " +
		// config.toString());
		// analizar();
		// }
		return melhorConfig;

	}

	ConfiguracoesDTO configuracoes = new ConfiguracoesDTO();

	public ConfiguracoesDTO getConfigDefault() {

		// configuracoes.setMaisAtrazado(getnumRand());
		this.configuracoes.setMaisFrequente(
				this.configuracoes.getMaisFrequente() <= 60 && this.configuracoes.getMenosFrequente() == 60
						? this.configuracoes.getMaisFrequente() + 1 : this.configuracoes.getMaisFrequente());
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

		List<MS> list = this.msRepository.findAll();

		this.combinacoesServices.carregarLista(list);

		List<JGDerivadoValidacao> listaA = ListaA.LISTA_A;
		List<JGDerivadoValidacao> listaB = ListaB.LISTA_B;
		List<JGDerivadoValidacao> listaC = ListaC.LISTA_C;
		List<JGDerivadoValidacao> listaD = ListaD.LISTA_D;

	}

	@PostConstruct
	public void iniciarAnaliseCombinacoes() {
		HashMap<PARAM, Object> params = new HashMap<>();
		this.analiseCombinacoes.init(params);
	}

	public long total() {
		return this.msRepository.count();
	}

}
