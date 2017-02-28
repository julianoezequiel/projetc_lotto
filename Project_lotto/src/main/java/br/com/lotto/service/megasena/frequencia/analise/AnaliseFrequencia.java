package br.com.lotto.service.megasena.frequencia.analise;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lotto.dao.MegaSenaRepository;
import br.com.lotto.service.Analize;

@Service
public class AnaliseFrequencia implements Analize {

	@Autowired
	private MegaSenaRepository megaSenaRepository;

	// Analise mostra que os numeros se agrupam em 5 grupos , com media de
	// 8,9,10,11,12 -- sendo 12 o menos frequente e 8 o mais frequente
	@Override
	public void init() {
		Long countSorteio = this.megaSenaRepository.count();
		AtomicInteger i = new AtomicInteger(1);
		List<AnaliseFreqResult> listaResultado = new ArrayList<>();
		for (; i.get() <= countSorteio.intValue(); i.incrementAndGet()) {
			Collection<Object[]> listaFrequ = this.megaSenaRepository.getFrequencia(i.get());

			listaFrequ.stream().forEach(f -> {

				Object[] freq = f;
				listaResultado.add(new AnaliseFreqResult(i.get(), ((Integer) freq[0]).intValue(),
						((BigInteger) freq[1]).intValue(), (i.get() / ((BigInteger) freq[1]).intValue())));

				// System.out.println("Sorteio : " + i.get() + " - Número : " +
				// freq[0] + " - Frequência : " + freq[1]
				// + " ---- Cálculo média : " + i.get() / ((BigInteger)
				// freq[1]).intValue());

			});

		}
		// conta e agrupa os resultados pela media
		Map<Integer, Long> result = listaResultado.stream()
				.collect(Collectors.groupingBy(AnaliseFreqResult::getMedia, Collectors.counting()));

		Map<Integer, Long> finalMap = new LinkedHashMap<>();
		// ordena e adiciona ao map final
		result.entrySet().stream().sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
				.forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));

		System.out.println(finalMap);
	}

	/**
	 * Classe interna para auxiliar na analise
	 * 
	 * @author Juliano
	 *
	 */
	private class AnaliseFreqResult {

		private final Integer sorteio;
		private final Integer numero;
		private final Integer frequencia;
		private final Integer media;

		public Integer getMedia() {
			return media;
		}

		public AnaliseFreqResult(Integer sorteio, Integer numero, Integer frequencia, Integer media) {
			super();
			this.sorteio = sorteio;
			this.numero = numero;
			this.frequencia = frequencia;
			this.media = media;
		}

	}

}