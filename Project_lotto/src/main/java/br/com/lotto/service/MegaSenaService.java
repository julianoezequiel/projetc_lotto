/**
 * 
 */
package br.com.lotto.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.lotto.dao.MegaSenaRepository;
import br.com.lotto.dto.Jogos;
import br.com.lotto.dto.MegaSenaDTO;
import br.com.lotto.dto.RespostaValidacao;
import br.com.lotto.dto.AtrazoDTO;
import br.com.lotto.dto.Configuracoes;
import br.com.lotto.dto.FrequenciaDTO;
import br.com.lotto.entity.Megasena;
import br.com.lotto.entity.Numero;
import br.com.lotto.service.analize.AtrazoAnalizeService;
import br.com.lotto.service.analize.FrequenciaAnalizeService;
import br.com.lotto.service.validacoes.MaisFrequenteValidacaoService;
import br.com.lotto.service.validacoes.MenosFrequenteValidacaoService;

/**
 * @author Juliano
 *
 */
@Service
public class MegaSenaService {

	@Autowired
	private MegaSenaRepository megaSenaRepository;

	@Autowired
	private FrequenciaAnalizeService frequenciaAnalizeService;

	@Autowired
	private AtrazoAnalizeService atrazoAnalizeservice;

	@Autowired
	private MaisFrequenteValidacaoService maisFrequenteValidacaoService;

	@Autowired
	private MenosFrequenteValidacaoService menosFrequenteValidacaoService;

	/**
	 * Lista todos os concursos
	 * 
	 * @return
	 */
	public Collection<MegaSenaDTO> buscartodos() {
		Collection<MegaSenaDTO> list = new ArrayList<>();
		this.megaSenaRepository.findAll().stream().forEach(s -> {
			list.add(s.toMegaSenaDTO());
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

		this.megaSenaRepository.findAll().stream().forEach(s -> {
			list.add(new Jogos(s.getConcurso(), s.getNumeroCollection()));
		});
		return list;
	}

	/**
	 * Busca o concurso pelo id do concuso
	 * 
	 * @param id
	 * @return
	 */
	public MegaSenaDTO buscarPorId(Integer id) throws ServiceException {
		Megasena megasena = this.megaSenaRepository.findOne(id);
		if (megasena != null) {
			return megasena.toMegaSenaDTO();
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
		return frequenciaAnalizeService.buscarFrequencias();
	}

	/**
	 * Calcula os atrazos das dezenas noc concursos realizados
	 * 
	 * @return
	 */
	public Collection<AtrazoDTO> buscarAtrazos() {
		return this.atrazoAnalizeservice.buscarAtrazos();
	}

	public Collection<RespostaValidacao> validarFrequencia(Jogos jogos, Configuracoes config) {
		Collection<RespostaValidacao> validacoes = new ArrayList<>();
		validacoes.add(this.maisFrequenteValidacaoService.validar(config, jogos));
		validacoes.add(this.menosFrequenteValidacaoService.validar(config, jogos));
		return validacoes;
	}

	private HashMap<Long, Configuracoes> melhorConfig = new HashMap<>();
	private int totalTentativas = 1000;
	private int passo = 0;
	private Long max = 0l;

	public HashMap<Long, Configuracoes> analizar() {
		max = 0l;
		totalTentativas = 1000;
		passo = 0;
		melhorConfig = new HashMap<>();
		Collection<Jogos> jogos = this.buscartodosConcursos();
		return analizar(jogos);
	}

	public HashMap<Long, Configuracoes> analizar(Collection<Jogos> jogos) {
		Configuracoes config = getConfigDefault();
		Collection<RespostaValidacao> validacoes = new ArrayList<>();
		jogos.stream().forEach(jogo -> {
			validacoes.add(this.maisFrequenteValidacaoService.validar(config, jogo));
			validacoes.add(this.menosFrequenteValidacaoService.validar(config, jogo));
		});

		long subTotal = validacoes.stream().filter(f -> f.getAprovado().equals(true)).count();

		if (subTotal > max) {
			max = subTotal;
			melhorConfig.clear();
			melhorConfig.put(max, config.newIntance());
		}

		if (config.getMaisFrequente() != 60 || config.getMenosFrequente() != 60) {
			passo++;
			System.out.println("Teste : " + passo + " validação : " + config.toString());
			analizar(jogos);
		}

		return melhorConfig;

	}

	Configuracoes configuracoes = new Configuracoes();

	public Configuracoes getConfigDefault() {

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

}
