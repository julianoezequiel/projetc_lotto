package br.com.ottol.service.ms.atraso.analise;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ottol.dto.AtrasoDTO;
import br.com.ottol.dto.Jogos;
import br.com.ottol.entity.Numero;
import br.com.ottol.service.ms.MSService;
import br.com.ottol.service.numero.NumeroService;

@Service
public class AnaliseAtraso {

	@Autowired
	private NumeroService numeroService;

	@Autowired
	private MSService msService;

	/**
	 * Calcula os atrasos das dezenas nos concursos realizados,
	 * corrigido 28/02/2017, alterado para Megasenanumero
	 * 
	 * @return
	 */
	public Collection<AtrasoDTO> buscarAtrasos(Integer maxConc) {
		
		// Busca os numeros na base
		Collection<Jogos> listaNumeros = this.msService.buscarMenorQueConcursos(maxConc);

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
		return null;
	}

}
