package br.com.lotto.service.ms.frequencia.analise;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lotto.dao.MegaSenaRepository;
import br.com.lotto.service.Analize;

@Service
public class AnaliseFrequencia implements Analize {

	public final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AnaliseFrequencia.class.getName());

	@Autowired
	private MegaSenaRepository megaSenaRepository;

	// analize das frequencias dos numeros de forma retroativa. Iniciando no
	// primeiro resultado ao ultimo
	// validando sua frequencia e sua media.
	// o resultado é dado pela repetição da media. Quanto maior fator de
	// repetição da media da frquencia maior
	// as chances da dezena
	@Override
	public void init(HashMap<Object, Object> params) {
		//define a partir de qual concurso irá começar a validação
		Integer inicio = (Integer) params.get("Inicio");
		//define se é para validar somente um numero ou todos
		Integer numeroFiltro =  (Integer) params.get("numFilter");

		Long countSorteio = this.megaSenaRepository.count();
		AtomicInteger i = new AtomicInteger(inicio);
		List<AnaliseFreqResult> listaResultado = new ArrayList<>();
		for (; i.get() <= countSorteio.intValue(); i.incrementAndGet()) {
			Collection<Object[]> listaFrequ = this.megaSenaRepository.getFrequencia(i.get());

			listaFrequ.stream().filter(f -> {
				if (numeroFiltro > 0) {
					return ((Integer) f[0]).intValue() == numeroFiltro;
				} else {
					return true;
				}
			}).forEach(f -> {

				Object[] freq = f;
				Double media = new BigDecimal(((BigInteger) freq[1]).doubleValue() * 100 / i.get())
						.setScale(2, RoundingMode.HALF_UP).doubleValue();

				listaResultado.add(new AnaliseFreqResult(i.get(), ((Integer) freq[0]).intValue(),
						((BigInteger) freq[1]).intValue(), media));
//				LOGGER.info("Sorteio : " + i.get() + " - Número : " + freq[0] + " - Frequência : " + freq[1]
//						+ " ---- Cálculo média : " + media);

			});

		}
		// conta e agrupa os resultados pela media
		Map<Double, Long> result = listaResultado.stream()
				.collect(Collectors.groupingBy(AnaliseFreqResult::getMedia, Collectors.counting()));

		Map<Double, Long> finalMap = new LinkedHashMap<>();
		// ordena e adiciona ao map final
		result.entrySet().stream().sorted(Map.Entry.<Double, Long>comparingByValue().reversed())
				.forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));

		LOGGER.info(finalMap.toString());
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
		private final Double media;

		public Double getMedia() {
			return media;
		}

		public AnaliseFreqResult(Integer sorteio, Integer numero, Integer frequencia, Double media) {
			super();
			this.sorteio = sorteio;
			this.numero = numero;
			this.frequencia = frequencia;
			this.media = media;
		}

	}

}
