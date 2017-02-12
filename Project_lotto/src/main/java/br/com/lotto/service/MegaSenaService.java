/**
 * 
 */
package br.com.lotto.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lotto.dao.MegaSenaRepository;
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

	public Collection<Megasena> buscartodos() {
		return this.megaSenaRepository.findAll();
	}

	public Megasena buscarPorId(Integer id) {
		return this.megaSenaRepository.findOne(id);
	}

	/**
	 * Calcula quantas vezes cada dezena ja foi sorteada
	 * @return
	 */
	public Collection<frequenciaDTO> buscarFrequencias() {

		List<Megasena> listaConcursos = (List<Megasena>) this.buscartodos();
		Collection<frequenciaDTO> frequencias = new ArrayList<>();
		AtomicInteger count = new AtomicInteger(1);

		for (; count.get() <= 60; count.incrementAndGet()) {

			Long total = listaConcursos.stream().filter(c -> c.getNumero1().getIdNumero() == count.get()
					|| c.getNumero2().getIdNumero() == count.get() || c.getNumero3().getIdNumero() == count.get()
					|| c.getNumero4().getIdNumero() == count.get() || c.getNumero5().getIdNumero() == count.get()
					|| c.getNumero6().getIdNumero() == count.get()).count();

			frequencias.add(new frequenciaDTO(count.get(), total.intValue()));

		}

		return frequencias;
	}

	/**
	 * Calcula os atrazos das dezenas
	 * @return
	 */
	public Collection<atrazoDTO> buscarAtrazos() {

		// Busca os numeros na base
		Collection<Numero> listaNumeros = this.numeroService.buscarTodos();

		List<atrazoDTO> listaAtrazo = new ArrayList<>();
		
		Long ultimoConcurso = this.megaSenaRepository.count();
		//realiza a interacao com a lista para recuperar todos os concursos
		listaNumeros.stream().forEach(numero -> {
			List<Megasena> listaMega = new ArrayList<>();
			listaMega.addAll(numero.getMegasenas1());
			listaMega.addAll(numero.getMegasenas2());
			listaMega.addAll(numero.getMegasenas3());
			listaMega.addAll(numero.getMegasenas4());
			listaMega.addAll(numero.getMegasenas5());
			listaMega.addAll(numero.getMegasenas6());
			//Busca o ultimo concurso que a dezena aparece
			Megasena megasena = listaMega.stream()
					.sorted(Comparator.comparing(Megasena::getIdConcurso).reversed())
					.findFirst().orElse(null);
			if (megasena!=null) {
				//Calcula a diferenca do ultimo concurso realizado para o ultimo em que o numero apareceu
				int qtdAtrazo = ultimoConcurso.intValue() - megasena.getConcurso();
				listaAtrazo.add(new atrazoDTO(numero.getIdNumero(), qtdAtrazo));
			}
		});
		
		//Retorna a lista ordenada pelo maior atrazo
		return listaAtrazo.stream()
				.sorted(Comparator.comparing(atrazoDTO::getAtrazo).reversed())
				.collect(Collectors.toList()) ;
	}

}