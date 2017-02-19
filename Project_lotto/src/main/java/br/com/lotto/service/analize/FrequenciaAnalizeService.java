package br.com.lotto.service.analize;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lotto.dao.MegaSenaRepository;
import br.com.lotto.dto.FrequenciaDTO;
import br.com.lotto.utils.Utils;

@Service
public class FrequenciaAnalizeService {

	@Autowired
	private MegaSenaRepository megaSenaRepository;

	private Collection<FrequenciaDTO> maisFrequenteList;
	private Collection<FrequenciaDTO> menosfrequenteList;
	private Date ultimaAtualizacao;

	public FrequenciaAnalizeService() {
		super();
		this.ultimaAtualizacao = new Date();
	}

	/**
	 * Calcula quantas vezes cada dezena ja foi sorteada
	 * 
	 * @return
	 */
	public Collection<FrequenciaDTO> buscarFrequencias(Boolean ordenarNumero, Boolean ordenarFrequencia) {

		Collection<FrequenciaDTO> frequencias = new ArrayList<>();
		int count = 0;
		Object[] objFreq = this.megaSenaRepository.getFrequencia();

		if (objFreq.length > 0) {
			for (; count < objFreq.length; count++) {
				Object[] itemFrequencia = (Object[]) objFreq[count];
				frequencias.add(new FrequenciaDTO((Integer) itemFrequencia[0], (BigInteger) itemFrequencia[1]));

			}
		}
		if (ordenarNumero != null && ordenarNumero) {
			return frequencias.stream().sorted(Comparator.comparing(FrequenciaDTO::getNumero))
					.collect(Collectors.toList());
		} else if (ordenarFrequencia != null && ordenarFrequencia) {
			return frequencias.stream().sorted(Comparator.comparing(FrequenciaDTO::getFrequencia))
					.collect(Collectors.toList());
		} else {
			return frequencias;
		}

	}

	public Collection<FrequenciaDTO> buscarFrequencias() {
		return this.buscarFrequencias(null, true);
	}

	/**
	 * Busca os numeros mais ferquentes de acordo com a quantidade passada.
	 * 
	 * @param qtd
	 * @return
	 */
	public Collection<FrequenciaDTO> getMaisFrequente(Integer qtd) {
		if (this.maisFrequenteList == null || Utils.adicionaMin(ultimaAtualizacao, 1).before(new Date())) {
			System.out.println("Atualiza lista de maior frequencia");
			ultimaAtualizacao = new Date();
			this.maisFrequenteList = this.buscarFrequencias(null, true);
		}
		return this.maisFrequenteList.stream()
				.skip(this.maisFrequenteList.size() - (qtd != null ? qtd : this.maisFrequenteList.size()))
				.limit(this.maisFrequenteList.size()).collect(Collectors.toList());
	}

	/**
	 * Busca os numeros menos ferquentes de acordo com a quantidade passada.
	 * 
	 * @param qtd
	 * @return
	 */
	public Collection<FrequenciaDTO> getMenosFrequente(Integer qtd) {
		if (this.menosfrequenteList == null || Utils.adicionaMin(ultimaAtualizacao, 1).before(new Date())) {
			System.out.println("Atualiza lista de menor frequencia");
			ultimaAtualizacao = new Date();
			this.menosfrequenteList = this.buscarFrequencias(null, true);
		}
		return this.menosfrequenteList.stream().sorted(Comparator.comparing(FrequenciaDTO::getFrequencia).reversed())
				.skip(this.menosfrequenteList.size() - (qtd != null ? qtd  : this.menosfrequenteList.size()))
				.limit(this.menosfrequenteList.size()).collect(Collectors.toList());
	}

}
