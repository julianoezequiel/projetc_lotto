package br.com.lotto.service.analize;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lotto.dto.AtrazoDTO;
import br.com.lotto.dto.Jogos;
import br.com.lotto.entity.Numero;
import br.com.lotto.service.MegaSenaService;
import br.com.lotto.service.NumeroService;

@Service
public class AtrazoAnalizeService {

	@Autowired
	private NumeroService numeroService;

	@Autowired
	private MegaSenaService megaSenaservice;

	/**
	 * Calcula os atrazos das dezenas noc concursos realizados
	 * 
	 * @return
	 */
	public Collection<AtrazoDTO> buscarAtrazos() {

		// Busca os numeros na base
		Collection<Jogos> listaNumeros = this.megaSenaservice.buscartodosConcursos();

		List<AtrazoDTO> listaAtrazo = new ArrayList<>();

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
				listaAtrazo.add(new AtrazoDTO(numero.getIdnumero(), qtdAtrazo));
			}
		});
		System.out.println("Total match : " + listaAtrazo.size());
		// Retorna a lista ordenada pelo maior atrazo
		return listaAtrazo.stream().sorted(Comparator.comparing(AtrazoDTO::getAtrazo).reversed())
				.collect(Collectors.toList());
	}

	/**
	 * Retorna uma lista com numeros de maior atrazo, o parametro determina o
	 * tamanho da lista
	 * 
	 * @param qtd
	 * @return
	 */
	public Collection<AtrazoDTO> getTopAtrazo(Integer qtd) {
		Collection<AtrazoDTO> list = this.buscarAtrazos();
		return list.stream().skip(list.size() - qtd).limit(list.size()).collect(Collectors.toList());
	}

}
