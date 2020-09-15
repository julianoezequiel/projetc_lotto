package br.com.ottol.service.ms.comb.validacao;

import java.util.ArrayList;
import java.util.Comparator;
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
public class ListaB implements Validacao {
	public final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ListaB.class.getName());

	@Autowired
	private MSRepository msRepository;

	@Override
	public RespostaValidacao validar(ConfiguracoesDTO config, Ppt ppt, List<JGDerivadoValidacao> LISTA_B) {
		LOGGER.debug("LISTA B");
		if (ppt.getConfiguracoesDTO().getLimiteB().getMax() == -1) {
			return new RespostaValidacao(this.getClass().getSimpleName(), true, true);
		} else {
			List<JGDerivadoValidacao> listaRet = new ArrayList<>();
			List<Numero> collect = ppt.getNumeroCollection().stream()
					.sorted(Comparator.comparing(NumeroDTO::getIdNumero)).map(m -> new Numero(m.getIdNumero()))
					.collect(Collectors.toList());
			collect.stream().forEach(n -> {
				criarSubListB1(ppt.getMegasenaidconcurso().getIdconcurso(), new ArrayList<>(collect), n, listaRet);
			});

			AtomicInteger repetido = new AtomicInteger(0);
			AtomicInteger repetidoMaior = new AtomicInteger(0);
			ArrayList<JGDerivadoValidacao> listaB = new ArrayList<>(LISTA_B);
			listaRet.stream().forEach(lt -> {
				listaB.stream().forEach(pe -> {
					boolean equals = lt.getNumeros().equals(pe.getNumeros());
					if (Boolean.TRUE.equals(equals)) {
						repetido.getAndIncrement();
						Integer concurso = lt.getConcurso();
						repetidoMaior.set(repetido.get() > repetidoMaior.get() ? repetido.get() : repetidoMaior.get());
						LOGGER.debug("LISTA B Integer c " + concurso + " - N:" + pe.getNumeros());
					}
				});
				repetido.set(0);
			});
			return new RespostaValidacao(this.getClass().getSimpleName(),
					config.getLimiteB().isValido(repetidoMaior.get()), repetidoMaior.get());
		}
	}

	public List<JGDerivadoValidacao> carregarListaEmMemoria(List<MS> list) {
		List<JGDerivadoValidacao> LISTA_B = new ArrayList<JGDerivadoValidacao>();
		list.stream().forEach(ms -> criarTipoListaB(ms, LISTA_B));
		return LISTA_B;
	}

	private void criarTipoListaB(MS ms, List<JGDerivadoValidacao> LISTA_B) {

		List<Numero> list = ms.getMegasenanumeroCollection().stream().map(m -> m.getNumero())
				.collect(Collectors.toList());

		list.stream().forEach(n -> criarSubListB1(ms.getConcurso(),
				list.stream().map(m -> m.clone()).collect(Collectors.toList()), n, LISTA_B));

	}

	private void criarSubListB1(Integer concurso, List<Numero> list, Numero n, List<JGDerivadoValidacao> listaRet) {
		JGDerivadoValidacao jgDerivadoValidacao = new JGDerivadoValidacao();
		jgDerivadoValidacao.setConcurso(concurso);
		list.remove(n);
		jgDerivadoValidacao.setNumeros(list);
		listaRet.add(jgDerivadoValidacao);
	}

	public Map<String, Long> frequencia(List<JGDerivadoValidacao> LISTA_B) {
		Map<String, Long> collect = LISTA_B.stream()
				.collect(Collectors.groupingBy(p -> p.getNum(), Collectors.counting()));
		return collect.entrySet().stream()
				.sorted((e2, e1) -> Long.compare(e1.getValue().longValue(), e2.getValue().longValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

	public RespostaValidacao validar(Ppt ppt, List<JGDerivadoValidacao> LISTA_B) {
		return this.validar(ppt.getConfiguracoesDTO(), ppt, LISTA_B);
	}

}
