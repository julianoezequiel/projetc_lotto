package br.com.ottol.service.ms.comb.validacao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ottol.dao.MSRepository;
import br.com.ottol.dto.ConfiguracoesDTO;
import br.com.ottol.dto.NumeroDTO;
import br.com.ottol.dto.PalpiteDTO;
import br.com.ottol.dto.RespostaValidacao;
import br.com.ottol.entity.MS;
import br.com.ottol.entity.Numero;
import br.com.ottol.service.Validacao;
import br.com.ottol.service.ms.JGDerivadoValidacao;

@Component
public class ListaA implements Validacao {
	public final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ListaA.class.getName());
	public static List<JGDerivadoValidacao> LISTA_A = new ArrayList<>();

	@Autowired
	private MSRepository msRepository;

	@Override
	public RespostaValidacao validar(ConfiguracoesDTO config, PalpiteDTO palpiteDTO) {
//		LOGGER.debug("LISTA A");
		AtomicInteger repetido = new AtomicInteger(0);
		
		ArrayList<JGDerivadoValidacao> newList = new ArrayList<>(LISTA_A);
		
		newList.stream().forEach(p -> {
			List<Numero> collect = palpiteDTO.getNumeroCollection().stream()
					.sorted(Comparator.comparing(NumeroDTO::getIdNumero)).map(m -> new Numero(m.getIdNumero()))
					.collect(Collectors.toList());

			boolean equals = collect.equals(p.getNumeros());
			if (Boolean.TRUE.equals(equals)) {
				repetido.getAndIncrement();
//				Integer concurso = p.getConcurso();
//				System.out.println("LISTA A Integer c " + concurso + " - N:" + collect);
			}
		});
		return new RespostaValidacao("Lista B", repetido.get() == 0, repetido.get());
	}

	public void carregarListaEmMemoria(List<MS> list) {
		LISTA_A = list.stream().map(m -> {
			return criarTipoListaA(m);
		}).collect(Collectors.toList());
		LOGGER.debug("Lista A criada");
	}

	private JGDerivadoValidacao criarTipoListaA(MS ms) {
		JGDerivadoValidacao jgDerivadoValidacao = new JGDerivadoValidacao();
		jgDerivadoValidacao.setConcurso(ms.getConcurso());
		jgDerivadoValidacao.setNumerosFromMs(ms.getMegasenanumeroCollection());
		return jgDerivadoValidacao;
	}

	public Map<String, Long> frequencia() {
		Map<String, Long> collect = LISTA_A.stream()
				.collect(Collectors.groupingBy(p -> p.getNum(), Collectors.counting()));
		return collect.entrySet().stream()
				.sorted((e2, e1) -> Long.compare(e1.getValue().longValue(), e2.getValue().longValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

	public RespostaValidacao validar(PalpiteDTO palpiteDTO) {
		return this.validar(palpiteDTO.getConfiguracoesCollection().stream().findFirst().orElse(new ConfiguracoesDTO()),
				palpiteDTO);

	}

}
