package br.com.ot.service.ms.atraso.analise;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import br.com.ot.dao.MSRepository;
import br.com.ot.dto.AtrasoDTO;
import br.com.ot.dto.Jgs;
import br.com.ot.dto.NumeroDTO;
import br.com.ot.dto.Ppt;
import br.com.ot.entity.MS;
import br.com.ot.entity.Numero;
import br.com.ot.service.Analise;
import br.com.ot.service.ms.MSService;
import br.com.ot.service.num.NumeroService;
import br.com.ot.utils.CONSTANTES.PARAM;

@Service
@Scope(value="prototype", proxyMode=ScopedProxyMode.TARGET_CLASS) 
public class AnaliseAtraso implements Analise {

	public final static Logger LOGGER = LoggerFactory.getLogger(AnaliseAtraso.class.getName());

	@Autowired
	private NumeroService numeroService;
	@Autowired
	private MSService msService;
	@Autowired
	private MSRepository msRepository;

	/**
	 * Calcula os atrasos das dezenas nos concursos realizados, corrigido
	 * 28/02/2017, alterado para Mgsnum
	 * 
	 * @return
	 */
	public Collection<AtrasoDTO> buscarAtrasos(Long maxConc, Integer numeroFiltro, Collection<Numero> nums) {
		// Busca os numeros na base
		Collection<Jgs> listaNumeros = this.msService.buscarMenorQueConcursos(maxConc);
		if (numeroFiltro != 0) {
			nums = nums.stream().filter(f -> f.getIdnumero() == numeroFiltro).collect(Collectors.toList());
		}

		return calculaAtraso(listaNumeros, nums);
	}

	public Collection<AtrasoDTO> calculaAtraso(Collection<Jgs> listaNumeros, Collection<Numero> nums) {
		Integer ultimoConcurso = listaNumeros.size();
		List<AtrasoDTO> listaAtraso = new ArrayList<>();
		nums.stream().filter(n -> !n.getMegasenanumeroCollection().isEmpty()).forEach(numero -> {
//			System.out.println("Comparador : " + num.toString());
			Jgs j = listaNumeros.stream().sorted(Comparator.comparing(Jgs::getConcurso).reversed())
					.filter(jogos -> {
						boolean exite = jogos.getNumeros().stream().map(r -> r.getNumero())
								.anyMatch(s -> s.equals(numero));
//						if (exite) {
//							System.out.println("Comparando : " + jogos.getNumeros());
//							System.out.println("Match : " + jogos.getConcurso());
//						}
						return exite;
					}).findFirst().orElse(null);
//			if (j != null) {
			int qtdAtraso = j != null ? (ultimoConcurso.intValue() - j.getConcurso()) : ultimoConcurso.intValue();
			listaAtraso.add(new AtrasoDTO(numero.getIdnumero(), qtdAtraso));
//			}
		});
//		System.out.println("Total match : " + listaAtraso.size());
		// Retorna a lista ordenada pelo maior atrazo
		return listaAtraso.stream().sorted(Comparator.comparing(AtrasoDTO::getAtual).reversed())
				.collect(Collectors.toList());
	}

	public Collection<AtrasoDTO> buscarAtrasosAvancado() {

		// Busca os numeros na base
		Collection<Jgs> listaNumeros = this.msService.buscartodosConcursos();

		List<AtrasoDTO> listaAtraso = new ArrayList<>();

		Integer ultimoConcurso = listaNumeros.size();

		Collection<Numero> nums = this.numeroService.buscarTodos();

		nums.stream().filter(n -> !n.getMegasenanumeroCollection().isEmpty()).forEach(numero -> {
			System.out.println("Comparador : " + numero.toString());
			Jgs j = listaNumeros.stream().sorted(Comparator.comparing(Jgs::getConcurso).reversed())
					.filter(jogos -> {
						boolean exite = jogos.getNumeros().stream().map(r -> r.getNumero())
								.anyMatch(s -> s.equals(numero));
//						if (exite) {
//							System.out.println("Comparando : " + jogos.getNumeros());
//							System.out.println("Match : " + jogos.getConcurso());
//						}
						return exite;
					}).findFirst().orElse(null);
//			if (j != null) {
			int qtdAtraso = j != null ? (ultimoConcurso.intValue() - j.getConcurso()) : ultimoConcurso.intValue();
			listaAtraso.add(new AtrasoDTO(numero.getIdnumero(), qtdAtraso));
//			}
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
		Collection<Numero> buscarTodos = this.numeroService.buscarTodos();
		return buscarAtrasos(maxConc, 0, buscarTodos);
	}

	@Override
	public HashMap<Object, Object> init(HashMap<PARAM, Object> params) {
		// define a partir de qual concurso irá começar a validação
		HashMap<Object, Object> map = new HashMap<>();
		Integer inicio = (Integer) params.get(PARAM.PARAM_INICIO);
		LinkedList<AtrasoDTO> result = new LinkedList<>();
		// define se é para validar somente um num ou todos
		Integer numeroFiltro = (Integer) params.get(PARAM.PARAM_NUMERO);
		Collection<Numero> buscarTodos = this.numeroService.buscarTodos();
		Collection<AtrasoDTO> list = buscarAtrasos(inicio.longValue(), numeroFiltro, buscarTodos);

		map.put(PARAM.RESULT, list);
		return map;
	}

	public Collection<AtrasoDTO> analizarRecursivo(Ppt ppt) {

		MS ultimo = this.msRepository.getUltimoConcurso();
		Collection<Numero> buscarTodos = this.numeroService.buscarTodos();
		Map<Integer, LinkedList<AtrasoDTO>> mapN = new HashMap<>();

		buscarTodos.stream().forEach(f -> {
			mapN.put(f.getIdnumero(), new LinkedList<AtrasoDTO>());
		});

		Collection<AtrasoDTO> list = new ArrayList<AtrasoDTO>();
		for (Integer i = ppt.getInit(); i < ultimo.getIdconcurso(); i++) {
			list = buscarAtrasos(i.longValue(), 0, buscarTodos);
			final int ii = i;
			list.stream().forEach(f -> {
				f.setConc(ii);
				mapN.get(f.getNumero()).addLast(f);
			});
		}

		mapN.entrySet().stream().forEach(f -> {
			AtomicInteger maior = new AtomicInteger(0);
			AtomicInteger ultim = new AtomicInteger(0);
			LinkedList<Integer> ciclo = new LinkedList<>();
			f.getValue().stream().forEach(ff -> {

				if (ff.getAtual() != 0) {
					ultim.set(ff.getAtual());
				} else {
					ciclo.addLast(ultim.get());
				}

				maior.set(ff.getAtual() > maior.get() ? ff.getAtual() : maior.get());
				ff.setMaiorJaRegistrado(maior.get());
				ff.setUltimoAtraso(ultim.get());
				ff.setCiclo(new LinkedList<>(ciclo));
			});
		});

		list.stream().forEach(f -> {
			Double collect = f.getCiclo().stream().collect(Collectors.summingDouble(Integer::doubleValue));
			f.setMediaCiclo(collect / Double.valueOf(f.getCiclo().size()));
//			f.setCiclo(new LinkedList<>(f.getCiclo().stream()
//					.skip(f.getCiclo().size() > 10 ? f.getCiclo().size() - 10l : 0).collect(Collectors.toList())));

		});

		LOGGER.debug("Termino");
		return list.stream().sorted(Comparator.comparing(AtrasoDTO::getMediaCiclo)).collect(Collectors.toList());
	}

}
