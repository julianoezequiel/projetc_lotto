package br.com.ot.service.ms.frequ.analise;

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
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import br.com.ot.dao.MSRepository;
import br.com.ot.dto.AnaliseFreqResult;
import br.com.ot.dto.Ppt;
import br.com.ot.service.Analise;
import br.com.ot.utils.CONSTANTES.PARAM;

@Service
@Scope(value="prototype", proxyMode=ScopedProxyMode.TARGET_CLASS) 
public class AnaliseFrequencia implements Analise {

	public final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AnaliseFrequencia.class.getName());

	@Autowired
	private MSRepository megaSenaRepository;

	// analize das frequencias dos numeros de forma retroativa. Iniciando no
	// primeiro resultado ao ultimo
	// validando sua frequencia e sua media.
	// o resultado é dado pela repetição da media. Quanto maior fator de
	// repetição da media da frquencia maior
	// as chances da dezena
	@Override
	public HashMap<Object, Object> init(HashMap<PARAM, Object> params) {
		// define a partir de qual concurso irá começar a validação
		Integer inicio = (Integer) params.get(PARAM.PARAM_INICIO);
		// define se é para validar somente um num ou todos
		Integer numeroFiltro = (Integer) params.get(PARAM.PARAM_NUMERO);

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
				LOGGER.info("S : " + i.get() + " - N : " + freq[0] + " - F : " + freq[1] + " ---- CM  : " + media);

			});

		}
		// conta e agrupa os resultados pela media
		Map<Double, Long> result = listaResultado.stream()
				.collect(Collectors.groupingBy(AnaliseFreqResult::getMedia, Collectors.counting()));

		HashMap<Object, Object> finalMap = new LinkedHashMap<>();
		// ordena e adiciona ao map final
		result.entrySet().stream().sorted(Map.Entry.<Double, Long>comparingByValue().reversed())
				.forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));

		LOGGER.info(finalMap.toString());

		return finalMap;
	}

	public List<AnaliseFreqResult> analiseRecursiva(Ppt ppt) {

		// define a partir de qual concurso irá começar a validação
		Integer inicio = ppt.getMegasenaidconcurso().getIdconcurso();
		// define se é para validar somente um num ou todos
		Integer numeroFiltro = 0;

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
				LOGGER.info("S : " + i.get() + " - N : " + freq[0] + " - F : " + freq[1] + " ---- CM  : " + media);

			});

		}
		// conta e agrupa os resultados pela media
		Map<Double, Long> result = listaResultado.stream()
				.collect(Collectors.groupingBy(AnaliseFreqResult::getMedia, Collectors.counting()));

		HashMap<Object, Object> finalMap = new LinkedHashMap<>();
		// ordena e adiciona ao map final
		result.entrySet().stream().sorted(Map.Entry.<Double, Long>comparingByValue().reversed())
				.forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));

		LOGGER.info(finalMap.toString());

		return listaResultado;
	}
}
