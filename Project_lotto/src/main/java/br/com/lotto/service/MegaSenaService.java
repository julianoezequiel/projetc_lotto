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
	 * Calcula os atrazos das dezenas
	 * 
	 * @return
	 */
	public Collection<atrazoDTO> buscarAtrazos() {

		// Busca os numeros na base
		Collection<Numero> listaNumeros = this.numeroService.buscarTodos();

		List<atrazoDTO> listaAtrazo = new ArrayList<>();

		Long ultimoConcurso = this.megaSenaRepository.count();
		// realiza a interacao com a lista para recuperar todos os concursos
		// listaNumeros.stream().forEach(numero -> {
		// List<Megasena> listaMega = new ArrayList<>();
		// listaMega.addAll(numero.getMegasenas1());
		// listaMega.addAll(numero.getMegasenas2());
		// listaMega.addAll(numero.getMegasenas3());
		// listaMega.addAll(numero.getMegasenas4());
		// listaMega.addAll(numero.getMegasenas5());
		// listaMega.addAll(numero.getMegasenas6());
		// Busca o ultimo concurso que a dezena aparece
		// Megasena megasena = listaMega.stream()
		// .sorted(Comparator.comparing(Megasena::getIdConcurso).reversed())
		// .findFirst().orElse(null);
		// if (megasena!=null) {
		// //Calcula a diferenca do ultimo concurso realizado para o ultimo em
		// que o numero apareceu
		// int qtdAtrazo = ultimoConcurso.intValue() - megasena.getConcurso();
		// listaAtrazo.add(new atrazoDTO(numero.getIdNumero(), qtdAtrazo));
		// }
		// });

		// Retorna a lista ordenada pelo maior atrazo
		return listaAtrazo.stream().sorted(Comparator.comparing(atrazoDTO::getAtrazo).reversed())
				.collect(Collectors.toList());
	}

}
