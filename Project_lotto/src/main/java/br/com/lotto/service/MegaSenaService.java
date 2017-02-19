/**
 * 
 */
package br.com.lotto.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

	public Collection<RespostaValidacao> validarFrequencia(Jogos jogos,Configuracoes config) {
		Collection<RespostaValidacao> validacoes = new ArrayList<>();
		validacoes.add(this.maisFrequenteValidacaoService.validar(config, jogos));
		validacoes.add(this.menosFrequenteValidacaoService.validar(config, jogos));
		return validacoes;
	}

}
