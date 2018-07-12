package br.com.ottol.service.ms.atraso.analise;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ottol.dto.AtrasoDTO;
import br.com.ottol.dto.Jogos;
import br.com.ottol.entity.Numero;
import br.com.ottol.service.Analise;
import br.com.ottol.service.ms.MSService;
import br.com.ottol.service.numero.NumeroService;
import br.com.ottol.utils.CONSTANTES.PARAM;

@Service
public class AnaliseAtraso implements Analise {

	@Autowired
	private NumeroService numeroService;

	@Autowired
	private MSService msService;

	/**
	 * Calcula os atrasos das dezenas nos concursos realizados, corrigido
	 * 28/02/2017, alterado para Megasenanumero
	 * 
	 * @return
	 */
	public Collection<AtrasoDTO> buscarAtrasos(Long maxConc, Integer numeroFiltro) {
		// Busca os numeros na base
		Collection<Jogos> listaNumeros = this.msService.buscarMenorQueConcursos(maxConc);
		Collection<Numero> numeros = this.numeroService.buscarTodos();
		if (numeroFiltro != 0) {
			numeros = numeros.stream().filter(f -> f.getIdnumero() == numeroFiltro).collect(Collectors.toList());
		}

		return calculaAtraso(listaNumeros, numeros);
	}

	public Collection<AtrasoDTO> calculaAtraso(Collection<Jogos> listaNumeros, Collection<Numero> numeros) {
		Integer ultimoConcurso = listaNumeros.size();
		List<AtrasoDTO> listaAtraso = new ArrayList<>();
		numeros.stream().filter(n -> !n.getMegasenanumeroCollection().isEmpty()).forEach(numero -> {
			System.out.println("Comparador : " + numero.toString());
			Jogos j = listaNumeros.stream().sorted(Comparator.comparing(Jogos::getConcurso).reversed())
					.filter(jogos -> {
						boolean exite = jogos.getNumeros().stream().map(r -> r.getNumero())
								.anyMatch(s -> s.equals(numero));
						if (exite) {
							System.out.println("Comparando : " + jogos.getNumeros());
							System.out.println("Match : " + jogos.getConcurso());
						}
						return exite;
					}).findFirst().orElse(null);
			if (j != null) {
				int qtdAtraso = ultimoConcurso.intValue() - j.getConcurso();
				listaAtraso.add(new AtrasoDTO(numero.getIdnumero(), qtdAtraso));
			}
		});
		System.out.println("Total match : " + listaAtraso.size());
		// Retorna a lista ordenada pelo maior atrazo
		return listaAtraso.stream().sorted(Comparator.comparing(AtrasoDTO::getAtual).reversed())
				.collect(Collectors.toList());
	}

	public Collection<AtrasoDTO> buscarAtrasosAvancado() {

		// Busca os numeros na base
		Collection<Jogos> listaNumeros = this.msService.buscartodosConcursos();

		List<AtrasoDTO> listaAtraso = new ArrayList<>();

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
							System.out.println("Match : " + jogos.getConcurso());
						}
						return exite;
					}).findFirst().orElse(null);
			if (j != null) {
				int qtdAtraso = ultimoConcurso.intValue() - j.getConcurso();
				listaAtraso.add(new AtrasoDTO(numero.getIdnumero(), qtdAtraso));
			}
		});
		System.out.println("Total match : " + listaAtraso.size());
		// Retorna a lista ordenada pelo maior atrazo
		return listaAtraso.stream().sorted(Comparator.comparing(AtrasoDTO::getAtual).reversed())
				.collect(Collectors.toList());
	}

	/**
	 * Retorna uma lista com numeros de maior atrazo, o parametro determina o
	 * tamanho da lista
	 * 
	 * @param qtd
	 * @return
	 */
	public Collection<AtrasoDTO> getTopAtraso(Integer qtd) {
		Collection<AtrasoDTO> list = this.buscarAtrasos();
		return list.stream().skip(list.size() - qtd).limit(list.size()).collect(Collectors.toList());
	}

	private Collection<AtrasoDTO> buscarAtrasos() {
		long maxConc = this.msService.total();
		return buscarAtrasos(maxConc, 0);
	}

	public void init(HashMap<PARAM, Object> params) {
		// define a partir de qual concurso irá começar a validação
		Integer inicio = (Integer) params.get(PARAM.PARAM_INICIO);
		// define se é para validar somente um numero ou todos
		Integer numeroFiltro = (Integer) params.get(PARAM.PARAM_NUMERO);

		buscarAtrasos(inicio.longValue(), numeroFiltro);
	}

}
