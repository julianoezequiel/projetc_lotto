package br.com.lotto.service.ms.frequencia;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lotto.dao.MegaSenaRepository;
import br.com.lotto.dto.FrequenciaDTO;

@Service
public class FrequenciaService {

	@Autowired
	private MegaSenaRepository megaSenaRepository;

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
				BigDecimal media = new BigDecimal(freq.intValue() * 100 / total).setScale(2, RoundingMode.HALF_UP);
				BigDecimal frequencia = new BigDecimal(freq).setScale(2, RoundingMode.HALF_UP);
				frequencias.add(new FrequenciaDTO((Integer) itemFrequencia[0], frequencia, media));
			});
		}
		return frequencias.stream().sorted(Comparator.comparing(FrequenciaDTO::getMedia).reversed())
				.collect(Collectors.toList());
	}

}
