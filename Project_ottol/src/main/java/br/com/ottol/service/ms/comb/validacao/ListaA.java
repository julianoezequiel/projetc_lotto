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
import br.com.ottol.dto.Ppt;
import br.com.ottol.dto.RespostaValidacao;
import br.com.ottol.entity.MS;
import br.com.ottol.entity.Numero;
import br.com.ottol.service.Validacao;
import br.com.ottol.service.ms.JGDerivadoValidacao;

@Component
public class ListaA implements Validacao {
	public final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ListaA.class.getName());

	@Autowired
	private MSRepository msRepository;

	@Override
	public RespostaValidacao validar(ConfiguracoesDTO config, Ppt ppt, List<JGDerivadoValidacao> LISTA_A) {
		LOGGER.debug("LISTA A");
		if (ppt.getConfiguracoesDTO().getLimiteA().getMax() == -1) {
			return new RespostaValidacao(this.getClass().getSimpleName(), true, true);
		} else {
			AtomicInteger repetido = new AtomicInteger(0);
			AtomicInteger repetidoMaior = new AtomicInteger(0);
			ArrayList<JGDerivadoValidacao> newList = new ArrayList<>(LISTA_A);

			newList.stream().forEach(p -> {
				List<Numero> collect = ppt.getNumeroCollection().stream()
						.sorted(Comparator.comparing(NumeroDTO::getIdNumero)).map(m -> new Numero(m.getIdNumero()))
						.collect(Collectors.toList());

				boolean equals = collect.equals(p.getNumeros());
				if (Boolean.TRUE.equals(equals)) {
					repetido.getAndIncrement();
					Integer concurso = p.getConcurso();
					repetidoMaior.set(repetido.get() > repetidoMaior.get() ? repetido.get() : repetidoMaior.get());
					LOGGER.debug("LISTA A Integer c " + concurso + " - N:" + collect);
				}
				repetido.set(0);
			});

			return new RespostaValidacao(this.getClass().getSimpleName(),
					config.getLimiteA().isValido(repetidoMaior.get()), repetidoMaior.get());
		}
	}

	public synchronized List<JGDerivadoValidacao> carregarListaEmMemoria(List<MS> list) {
		List<JGDerivadoValidacao> LISTA_A = new ArrayList<>();
		list.stream().forEach(m -> criarTipoListaA(m, LISTA_A));
		return LISTA_A;
	}

	private void criarTipoListaA(MS ms, List<JGDerivadoValidacao> listaRet) {
		JGDerivadoValidacao jgDerivadoValidacao = new JGDerivadoValidacao();
		jgDerivadoValidacao.setConcurso(ms.getConcurso());
		jgDerivadoValidacao.setNumerosFromMs(ms.getMegasenanumeroCollection());
		listaRet.add(jgDerivadoValidacao);
	}

	public Map<String, Long> frequencia(List<JGDerivadoValidacao> LISTA_A) {
		Map<String, Long> collect = LISTA_A.stream()
				.collect(Collectors.groupingBy(p -> p.getNum(), Collectors.counting()));
		return collect.entrySet().stream()
				.sorted((e2, e1) -> Long.compare(e1.getValue().longValue(), e2.getValue().longValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

	public RespostaValidacao validar(Ppt ppt, List<JGDerivadoValidacao> LISTA_A) {
		return this.validar(ppt.getConfiguracoesDTO(), ppt, LISTA_A);
	}

}
