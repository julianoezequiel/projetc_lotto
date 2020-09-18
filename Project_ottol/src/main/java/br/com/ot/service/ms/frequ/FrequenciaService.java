package br.com.ot.service.ms.frequ;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ot.dao.MSRepository;
import br.com.ot.dto.AnaliseFreqResult;
import br.com.ot.dto.AtrasoDTO;
import br.com.ot.dto.ConfiguracoesDTO;
import br.com.ot.dto.FrequenciaDTO;
import br.com.ot.dto.Ppt;
import br.com.ot.dto.RespostaValidacao;
import br.com.ot.service.ms.frequ.analise.AnaliseFrequencia;
import br.com.ot.utils.CONSTANTES;
import br.com.ot.utils.CONSTANTES.PARAM;

@Service
public class FrequenciaService {

	@Autowired
	private MSRepository megaSenaRepository;
	@Autowired
	private AnaliseFrequencia analiseFrequencia;

	public HashMap<Object, Object> analisarFrequencia() {
		HashMap<PARAM, Object> params = new HashMap<>();
		params.put(PARAM.PARAM_INICIO, 1);
		params.put(PARAM.PARAM_NUMERO, 0);
		return this.analiseFrequencia.init(params);
	}

	// Busca a lista completa de numeros e sua frequencias
	public Collection<FrequenciaDTO> buscarFrequencias() {
		Collection<FrequenciaDTO> frequencias = new ArrayList<>();
		Long total = this.megaSenaRepository.count();
		Collection<Object[]> list = this.megaSenaRepository.getFrequencia(total.intValue());

		if (!list.isEmpty()) {
			list.stream().forEach(o -> {
				Object[] itemFrequencia = (Object[]) o;
				BigInteger freq = (BigInteger) itemFrequencia[1];
				// BigInteger media = new
				// BigInteger(total.toString()).divide(((BigInteger)
				// itemFrequencia[1]));
				Double media = new BigDecimal(freq.doubleValue() * 100 / total).setScale(2, RoundingMode.HALF_UP)
						.doubleValue();
				BigDecimal frequencia = new BigDecimal(freq).setScale(2, RoundingMode.HALF_UP);
				frequencias.add(new FrequenciaDTO((Integer) itemFrequencia[0], frequencia, media));
			});
		}
		return frequencias.stream().sorted(Comparator.comparing(FrequenciaDTO::getMedia).reversed())
				.collect(Collectors.toList());
	}

	public Collection<FrequenciaDTO> buscarFrequencias(Ppt ppt) {
		Collection<FrequenciaDTO> frequencias = new ArrayList<>();
		Double total = Double.valueOf(ppt.getMegasenaidconcurso().getIdconcurso());
		Collection<Object[]> list = this.megaSenaRepository.getFrequencia(total.intValue());

		if (!list.isEmpty()) {
			list.stream().forEach(o -> {
				Object[] itemFrequencia = (Object[]) o;
				BigInteger freq = (BigInteger) itemFrequencia[1];
				// BigInteger media = new
				// BigInteger(total.toString()).divide(((BigInteger)
				// itemFrequencia[1]));
				Double media = new BigDecimal(freq.doubleValue() * 100d / total).setScale(2, RoundingMode.HALF_UP)
						.doubleValue();
				BigDecimal frequencia = new BigDecimal(freq).setScale(2, RoundingMode.HALF_UP);
				frequencias.add(new FrequenciaDTO((Integer) itemFrequencia[0], frequencia, media));
			});
		}
		return frequencias.stream().sorted(Comparator.comparing(FrequenciaDTO::getMedia).reversed())
				.collect(Collectors.toList());
	}
	
	public RespostaValidacao validarMaisFrequente(Ppt ppt, Collection<FrequenciaDTO> frequencias) {
		if (ppt.getConfiguracoesDTO().getMaisFrequente() == -1) {
			return new RespostaValidacao(CONSTANTES.TIPO_VALIDACAO.FREQPLUS.name(), true, true);
		} else {
			List<FrequenciaDTO> collect = frequencias.stream()
					.sorted(Comparator.comparing(FrequenciaDTO::getFrequencia).reversed())
					.limit(ppt.getConfiguracoesDTO().getMaisFrequente()).collect(Collectors.toList());

			AtomicInteger contains = new AtomicInteger(0);

			ppt.getNumeroCollection().stream().forEach(f -> {
				collect.stream().forEach(ff -> {
					if (f.getIdNumero().equals(ff.getNumero())) {
						contains.getAndIncrement();
					}
				});
			});
			return new RespostaValidacao(CONSTANTES.TIPO_VALIDACAO.FREQPLUS.name(), contains.get() > 0, contains.get());
		}
	}
	
	public RespostaValidacao validarMenosFrequente(Ppt ppt, Collection<FrequenciaDTO> frequencias) {
		if (ppt.getConfiguracoesDTO().getMenosFrequente() == -1) {
			return new RespostaValidacao(CONSTANTES.TIPO_VALIDACAO.FREQLESS.name(), true, true);
		} else {
			List<FrequenciaDTO> collect = frequencias.stream()
					.sorted(Comparator.comparing(FrequenciaDTO::getFrequencia))
					.limit(ppt.getConfiguracoesDTO().getMenosFrequente()).collect(Collectors.toList());

			AtomicInteger contains = new AtomicInteger(0);

			ppt.getNumeroCollection().stream().forEach(f -> {
				collect.stream().forEach(ff -> {
					if (f.getIdNumero().equals(ff.getNumero())) {
						contains.getAndIncrement();
					}
				});
			});
			return new RespostaValidacao(CONSTANTES.TIPO_VALIDACAO.FREQLESS.name(), contains.get() > 0, contains.get());
		}
	}
	
	public RespostaValidacao validarMenosMenosFrequente(Ppt ppt, Collection<FrequenciaDTO> frequencias) {
		RespostaValidacao validarMenosFrequente = validarMenosFrequente(ppt, frequencias);
		RespostaValidacao validarMaisFrequente = validarMaisFrequente(ppt, frequencias);
		return validarMaisFrequente;
	}

	public List<AnaliseFreqResult> analiseRecursiva(Ppt ppt) {
		return this.analiseFrequencia.analiseRecursiva(ppt);
	}
}
