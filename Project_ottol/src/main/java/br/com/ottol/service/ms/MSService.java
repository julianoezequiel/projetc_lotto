/**
 *
 */
package br.com.ottol.service.ms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.ottol.dao.MSRepository;
import br.com.ottol.dto.ConfiguracoesDTO;
import br.com.ottol.dto.Jogos;
import br.com.ottol.dto.MSDTO;
import br.com.ottol.dto.MegaSenaResultadoSimples;
import br.com.ottol.entity.MS;
import br.com.ottol.service.ServiceException;
import br.com.ottol.service.ms.combinacoes.CombinacoesServices;
import br.com.ottol.service.ms.combinacoes.validacao.ListaA;
import br.com.ottol.service.ms.combinacoes.validacao.ListaB;
import br.com.ottol.service.ms.combinacoes.validacao.ListaC;
import br.com.ottol.service.ms.combinacoes.validacao.ListaD;
import br.com.ottol.service.ms.frequencia.FrequenciaService;

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
	private CombinacoesServices combinacoesServices;

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

	public long total() {
		return this.msRepository.count();
	}

}
