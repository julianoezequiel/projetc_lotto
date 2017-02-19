/**
 * 
 */
package br.com.lotto.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.lotto.dao.MegaSenaRepository;
import br.com.lotto.dto.Jogos;
import br.com.lotto.dto.MegaSenaDTO;
import br.com.lotto.dto.atrazoDTO;
import br.com.lotto.dto.frequenciaDTO;
import br.com.lotto.entity.Megasena;
import br.com.lotto.entity.Numero;

/**
 * @author Juliano
 *
 */
@Service
public class MegaSenaService {

	@Autowired
	private MegaSenaRepository megaSenaRepository;

	@Autowired
	private NumeroService numeroService;

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
	public Collection<frequenciaDTO> buscarFrequencias() {

		Collection<frequenciaDTO> frequencias = new ArrayList<>();
		int count = 0;
		Object[] objFreq = this.megaSenaRepository.getFrequencia();

		if (objFreq.length > 0) {
			for (; count < objFreq.length; count++) {
				Object[] itemFrequencia = (Object[]) objFreq[count];
				frequencias.add(new frequenciaDTO((Integer) itemFrequencia[0], (BigInteger) itemFrequencia[1]));

			}
		}

		return frequencias;
	}

	/**
	 * Calcula os atrazos das dezenas noc concursos realizados
	 * 
	 * @return
	 */
	public Collection<atrazoDTO> buscarAtrazos() {

		// Busca os numeros na base
		Collection<Jogos> listaNumeros = this.buscartodosConcursos();

		List<atrazoDTO> listaAtrazo = new ArrayList<>();

		Integer ultimoConcurso = listaNumeros.size();

		Collection<Numero> numeros = this.numeroService.buscarTodos();

		numeros.stream().filter(n -> !n.getMegasenaCollection().isEmpty()).forEach(numero -> {
			System.out.println("Comparador : " + numero.toString());
			Jogos j = listaNumeros.stream().sorted(Comparator.comparing(Jogos::getConcurso).reversed())
					.filter(jogos -> {
						boolean achou = jogos.getNumeros().contains(numero);
						if (achou) {
							System.out.println("Comparando : " + jogos.getNumeros());
							System.out.println("Macth : " + jogos.getConcurso());
						}
						return achou;
					}).findFirst().orElse(null);
			if (j != null) {
				int qtdAtrazo = ultimoConcurso.intValue() - j.getConcurso();
				listaAtrazo.add(new atrazoDTO(numero.getIdnumero(), qtdAtrazo));
			}
		});
		System.out.println("Total match : " + listaAtrazo.size());
		// Retorna a lista ordenada pelo maior atrazo
		return listaAtrazo.stream().sorted(Comparator.comparing(atrazoDTO::getAtrazo).reversed())
				.collect(Collectors.toList());
	}

}
