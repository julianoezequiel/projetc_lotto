package br.com.ottol.service.ms.combinacoes.validacao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
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
import br.com.ottol.entity.Megasenanumero;
import br.com.ottol.entity.Numero;
import br.com.ottol.service.Validacao;
import br.com.ottol.service.ms.JGDerivadoValidacao;

@Component
public class ListaB implements Validacao {
	public final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ListaB.class.getName());
	public static List<JGDerivadoValidacao> LISTA_B = new ArrayList<>();

	@Autowired
	private MSRepository msRepository;

	@Override
	public RespostaValidacao validar(ConfiguracoesDTO config, PalpiteDTO palpiteDTO) {
		LOGGER.debug("LISTA B");
		List<JGDerivadoValidacao> listaRet = new ArrayList<>();
		List<Numero> collect = palpiteDTO.getNumeroCollection().stream()
				.sorted(Comparator.comparing(NumeroDTO::getIdNumero)).map(m -> new Numero(m.getIdNumero()))
				.collect(Collectors.toList());
		collect.stream().forEach(n -> {
			criarSubListB1(palpiteDTO.getMegasenaidconcurso().getIdconcurso(), new ArrayList<>(collect), n, listaRet);
		});

		AtomicInteger repetido = new AtomicInteger(0);
		LISTA_B.stream().forEach(o -> {
			listaRet.stream().forEach(palpite -> {
				Collection<Numero> numeros = o.getNumeros();
				
				boolean equals = numeros.equals(palpite.getNumeros());
				if (Boolean.TRUE.equals(equals)) {
					repetido.getAndIncrement();
					Integer concurso = o.getConcurso();
					System.out.println("LISTA B Integer c " + concurso + " - N:" + numeros);
				}

			});
		});
		return new RespostaValidacao("Lista B", repetido.get() == 0, repetido.get());
	}

	public void carregarListaEmMemoria(List<MS> list) {
		list.stream().forEach(ms -> criarTipoListaB(ms));
		LOGGER.debug("Lista B criada");
	}

	private void criarTipoListaB(MS ms) {

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

	public Map<String, Long> frequencia() {
		Map<String, Long> collect = LISTA_B.stream()
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
