package br.com.ottol.service.ms.comb.validacao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
public class ListaC implements Validacao {

	public final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ListaC.class.getName());
	public static List<JGDerivadoValidacao> LISTA_C = new ArrayList<>();

	@Autowired
	private MSRepository msRepository;

	@Override
	public RespostaValidacao validar(ConfiguracoesDTO config, PalpiteDTO palpiteDTO) {
//		LOGGER.debug("LISTA C");
		List<JGDerivadoValidacao> listaRet = new ArrayList<>();
		List<Numero> collect = palpiteDTO.getNumeroCollection().stream()
				.sorted(Comparator.comparing(NumeroDTO::getIdNumero)).map(m -> new Numero(m.getIdNumero()))
				.collect(Collectors.toList());

		criarTipoListaC(palpiteDTO.getMegasenaidconcurso().getIdconcurso(), new ArrayList<>(collect), listaRet);
		AtomicInteger repetido = new AtomicInteger(0);
		ArrayList<JGDerivadoValidacao> newList = new ArrayList<>(LISTA_C);
		newList.stream().forEach(o -> {
			listaRet.stream().forEach(palpite -> {
				Collection<Numero> numeros = o.getNumeros();
				boolean equals = numeros.equals(palpite.getNumeros());
				if (Boolean.TRUE.equals(equals)) {
					repetido.incrementAndGet();
					Integer concurso = o.getConcurso();
//					System.out.println("LISTA C Integer c " + concurso + " - N:" + numeros);
				}
			});
		});
		return new RespostaValidacao(this.getClass().getSimpleName(), repetido.get() == 0, repetido.get());
	}

	public void carregarListaEmMemoria(List<MS> list) {

		list.stream().forEach(ms -> criarTipoListaC(ms.getIdconcurso(),
				ms.getMegasenanumeroCollection().stream().map(Megasenanumero::getNumero).collect(Collectors.toList()),
				LISTA_C));
//		LOGGER.debug("Lista C criada");
	}

	private void criarTipoListaC(Integer idConcurso, List<Numero> list, List<JGDerivadoValidacao> listaRet) {

		int index1 = list.size() - 1;
		int index2 = index1 - 1;

		while (index1 != 1 || index2 != 0) {

			Numero n1 = list.get(index1);
			Numero n2 = list.get(index2);

			criarSubListB1(idConcurso, list.stream().map(m -> m.clone()).collect(Collectors.toList()), n1, n2,
					listaRet);

			if (index2 == 0) {
				index1--;
				index2 = index1 - 1;
			} else {
				index2--;
			}

		}
	}

	private void criarSubListB1(Integer concurso, List<Numero> list, Numero n1, Numero n2,
			List<JGDerivadoValidacao> listaRet) {
		JGDerivadoValidacao jgDerivadoValidacao = new JGDerivadoValidacao();
		jgDerivadoValidacao.setConcurso(concurso);
		list.remove(n1);
		list.remove(n2);
		jgDerivadoValidacao.setNumeros(list);
		listaRet.add(jgDerivadoValidacao);
	}

	public Map<String, Long> frequencia() {
		Map<String, Long> collect = LISTA_C.stream()
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
