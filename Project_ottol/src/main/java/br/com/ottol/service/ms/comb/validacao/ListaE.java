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
import br.com.ottol.entity.Megasenanumero;
import br.com.ottol.entity.Numero;
import br.com.ottol.service.Validacao;
import br.com.ottol.service.ms.JGDerivadoValidacao;
import br.com.ottol.utils.DEZ;
import br.com.ottol.utils.Utils;

@Component
public class ListaE implements Validacao {
	public final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ListaE.class.getName());
	public static List<JGDerivadoValidacao> LISTA_E = new ArrayList<>();

	public static HashMap<String, List<DEZ>> map;

	public ListaE() {
		ListaE.map = Utils.MONTAR_LISTA_E();
	}

	@Autowired
	private MSRepository msRepository;

	@Override
	public RespostaValidacao validar(ConfiguracoesDTO config, PalpiteDTO palpiteDTO) {
//		LOGGER.debug("LISTA E");
		List<JGDerivadoValidacao> listaRet = new ArrayList<>();
		List<Numero> collect = palpiteDTO.getNumeroCollection().stream()
				.sorted(Comparator.comparing(NumeroDTO::getIdNumero)).map(m -> new Numero(m.getIdNumero()))
				.collect(Collectors.toList());

		criarTipoListaB(palpiteDTO.getMegasenaidconcurso().getIdconcurso(), new ArrayList<>(collect), listaRet);
		ArrayList<JGDerivadoValidacao> newList = new ArrayList<>(LISTA_E);
		AtomicInteger repetido = new AtomicInteger(0);
		AtomicInteger repetidoMaior = new AtomicInteger(0);
		listaRet.stream().forEach(o -> {
			newList.stream().forEach(palpite -> {
				Collection<Numero> numeros = o.getNumeros();

				boolean equals = numeros.equals(palpite.getNumeros());
				if (Boolean.TRUE.equals(equals)) {
					repetido.getAndIncrement();
					Integer concurso = o.getConcurso();
					repetidoMaior.set(repetido.get() > repetidoMaior.get() ? repetido.get() : repetidoMaior.get());
//					System.out.println("LISTA D Integer c " + concurso + " - N:" + numeros);
				}

			});
			repetido.set(0);
		});
		return new RespostaValidacao(this.getClass().getSimpleName(), repetidoMaior.get() == 0, repetidoMaior.get());
	}

	public void carregarListaEmMemoria(List<MS> list) {
		list.stream().forEach(ms -> criarTipoListaB(ms.getIdconcurso(),
				ms.getMegasenanumeroCollection().stream().map(Megasenanumero::getNumero).collect(Collectors.toList()),
				LISTA_E));
//		LOGGER.debug("Lista E criada");
	}

	private synchronized void criarTipoListaB(Integer idConcurso, List<Numero> list, List<JGDerivadoValidacao> listaRet) {

		AtomicInteger posisao = new AtomicInteger(1);

		// adiciona as posições corretas
		list.stream().forEach(m -> {
			m.setPosisao(DEZ.fromInt(posisao.getAndIncrement()));
		});

		// compara os itens dos mapas do tipo com a lista de resultado
		map.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(f -> {
			criarSubListD(idConcurso,
					list.stream().filter(n -> f.getValue().contains(n.getPosisao())).collect(Collectors.toList()),
					listaRet);
		});

	}

	/**
	 * Cria a lista
	 * 
	 * @param concurso
	 * @param list
	 */
	private synchronized void criarSubListD(Integer concurso, List<Numero> list, List<JGDerivadoValidacao> listaRet) {
		JGDerivadoValidacao jgDerivadoValidacao = new JGDerivadoValidacao();
		jgDerivadoValidacao.setConcurso(concurso);
		jgDerivadoValidacao.setNumeros(list.stream().map(m -> m.clone()).collect(Collectors.toList()));
		listaRet.add(jgDerivadoValidacao);
	}

	public Map<String, Long> frequencia() {
		Map<String, Long> collect = LISTA_E.stream()
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
