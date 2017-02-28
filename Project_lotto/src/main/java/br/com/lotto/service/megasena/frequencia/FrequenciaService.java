package br.com.lotto.service.megasena.frequencia;

import java.math.BigInteger;
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
	
	//Busca a lista completa de numeros e sua frequencias
	public Collection<FrequenciaDTO> buscarFrequencias() {
		Collection<FrequenciaDTO> frequencias = new ArrayList<>();
		Long total = this.megaSenaRepository.count();
		Collection<Object[]> list = this.megaSenaRepository.getFrequencia(total.intValue());

		if (!list.isEmpty()) {
			list.stream().forEach(o -> {
				Object[] itemFrequencia = (Object[]) o;
				BigInteger media = new BigInteger(total.toString()).divide(((BigInteger) itemFrequencia[1]));
				frequencias.add(new FrequenciaDTO((Integer) itemFrequencia[0], (BigInteger) itemFrequencia[1], media));
			});
		}
		return frequencias.stream().sorted(Comparator.comparing(FrequenciaDTO::getMedia).reversed())
				.collect(Collectors.toList());
	}

}
