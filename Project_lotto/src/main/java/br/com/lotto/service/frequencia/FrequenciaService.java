package br.com.lotto.service.frequencia;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lotto.dao.MegaSenaRepository;
import br.com.lotto.dto.Configuracoes;
import br.com.lotto.dto.FrequenciaDTO;

@Service
public class FrequenciaService {

	@Autowired
	private MegaSenaRepository megaSenaRepository;

	private Collection<FrequenciaDTO> maisFrequenteList;
	private Collection<FrequenciaDTO> menosfrequenteList;
	private Date ultimaAtualizacao;

	public FrequenciaService() {
		super();
		this.ultimaAtualizacao = new Date();
	}

	/**
	 * Calcula quantas vezes cada dezena ja foi sorteada
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	public Collection<FrequenciaDTO> buscarFrequencias(Boolean ordenarNumero, Boolean ordenarFrequencia,
			Configuracoes config) {

		Collection<FrequenciaDTO> frequencias = new ArrayList<>();

		// for (int i = 1; i <= 60; i++) {

		List<Object[]> list = this.megaSenaRepository.getFrequencia(config.getMaisFrequente());

		if (!list.isEmpty()) {
			list.stream().forEach(o -> {
				Object[] itemFrequencia = (Object[]) o;
				frequencias.add(new FrequenciaDTO((Integer) itemFrequencia[0], (BigInteger) itemFrequencia[1]));
			});
		}
		// }
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
		return this.buscarFrequencias(null, true, new Configuracoes());
	}

	/**
	 * Busca os numeros mais ferquentes de acordo com a quantidade passada.
	 * 
	 * @param qtd
	 * @return
	 */
	public Collection<FrequenciaDTO> getMaisFrequente(Configuracoes config) {
		// if (this.maisFrequenteList == null ||
		// Utils.adicionaMin(ultimaAtualizacao, 1).before(new Date())) {
		System.out.println("Atualiza lista de maior frequencia");
		// ultimaAtualizacao = new Date();
		this.maisFrequenteList = this.buscarFrequencias(null, true, config);
		// }
		return this.maisFrequenteList.stream().skip(this.maisFrequenteList.size()
				- (config.getMaisFrequente() != null ? config.getMaisFrequente() : this.maisFrequenteList.size()))
				.limit(this.maisFrequenteList.size()).collect(Collectors.toList());
	}

	/**
	 * Busca os numeros menos ferquentes de acordo com a quantidade passada.
	 * 
	 * @param qtd
	 * @return
	 */
	public Collection<FrequenciaDTO> getMenosFrequente(Configuracoes config) {
		// if (this.menosfrequenteList == null ||
		// Utils.adicionaMin(ultimaAtualizacao, 1).before(new Date())) {
		System.out.println("Atualiza lista de menor frequencia");
		ultimaAtualizacao = new Date();
		this.menosfrequenteList = this.buscarFrequencias(null, true, new Configuracoes());
		// }
		int skip = this.menosfrequenteList.size()
				- (config.getMenosFrequente() != null ? config.getMenosFrequente() : this.menosfrequenteList.size());
		return this.menosfrequenteList.stream().sorted(Comparator.comparing(FrequenciaDTO::getFrequencia).reversed())
				.skip(skip <= 0 ? 0 : skip).limit(this.menosfrequenteList.size()).collect(Collectors.toList());
	}
}
