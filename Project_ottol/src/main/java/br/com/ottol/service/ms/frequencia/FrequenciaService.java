package br.com.ottol.service.ms.frequencia;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ottol.dao.MSRepository;
import br.com.ottol.dto.ConfiguracoesDTO;
import br.com.ottol.dto.FrequenciaDTO;
import br.com.ottol.service.ms.frequencia.analise.AnaliseFrequencia;
import br.com.ottol.utils.CONSTANTES.PARAM;

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

}
