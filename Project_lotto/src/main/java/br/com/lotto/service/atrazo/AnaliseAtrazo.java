package br.com.lotto.service.atrazo;

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
public class AnaliseAtrazo {

	@Autowired
	private NumeroService numeroService;

	@Autowired
	private MegaSenaService megaSenaservice;

	/**
	 * Calcula os atrazos das dezenas nos concursos realizados,
	 * corrigido 28/02/2017, alterado para Megasenanumero
	 * 
	 * @return
	 */
	public Collection<AtrazoDTO> buscarAtrazos() {

		// Busca os numeros na base
		Collection<Jogos> listaNumeros = this.megaSenaservice.buscartodosConcursos();

		List<AtrazoDTO> listaAtrazo = new ArrayList<>();

		Integer ultimoConcurso = listaNumeros.size();

		Collection<Numero> numeros = this.numeroService.buscarTodos();

		numeros.stream().filter(n -> !n.getMegasenanumeroCollection().isEmpty()).forEach(numero -> {
			System.out.println("Comparador : " + numero.toString());
			Jogos j = listaNumeros.stream().sorted(Comparator.comparing(Jogos::getConcurso).reversed())
					.filter(jogos -> {
						boolean exite = jogos.getNumeros().stream().map(r -> r.getNumero())
								.anyMatch(s -> s.equals(numero));
						if (exite) {
							System.out.println("Comparando : " + jogos.getNumeros());
							System.out.println("Macth : " + jogos.getConcurso());
						}
						return exite;
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
