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
import br.com.ottol.entity.Megasenanumero;
import br.com.ottol.entity.Numero;
import br.com.ottol.service.Validacao;
import br.com.ottol.service.ms.JGDerivadoValidacao;
import br.com.ottol.utils.DEZ;
import br.com.ottol.utils.Utils;

@Component
public class ListaD implements Validacao {
	public final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ListaD.class.getName());
	public static List<JGDerivadoValidacao> LISTA_D = new ArrayList<>();

	public static HashMap<String, List<DEZ>> map;

	public ListaD() {
		ListaD.map = Utils.MONTAR_LISTA_D();
	}

	@Autowired
	private MSRepository msRepository;

	@Override
	public RespostaValidacao validar(ConfiguracoesDTO config, Ppt ppt) {
		LOGGER.debug("LISTA D");
		List<JGDerivadoValidacao> listaRet = new ArrayList<>();
		List<Numero> collect = ppt.getNumeroCollection().stream()
				.sorted(Comparator.comparing(NumeroDTO::getIdNumero)).map(m -> new Numero(m.getIdNumero()))
				.collect(Collectors.toList());

		criarTipoListaB(ppt.getMegasenaidconcurso().getIdconcurso(), new ArrayList<>(collect), listaRet);
		ArrayList<JGDerivadoValidacao> newList = new ArrayList<>(LISTA_D);
		AtomicInteger repetido = new AtomicInteger(0);
		AtomicInteger repetidoMaior = new AtomicInteger(0);
		listaRet.stream().forEach(lt -> {
			newList.stream().forEach(pe -> {
				boolean equals = lt.getNumeros().equals(pe.getNumeros());
				if (Boolean.TRUE.equals(equals)) {
					repetido.getAndIncrement();
					Integer concurso = pe.getConcurso();
					repetidoMaior.set(repetido.get() > repetidoMaior.get() ? repetido.get() : repetidoMaior.get());
					LOGGER.debug("LISTA D Integer c " + concurso + " - N:" + pe.getNumeros());
				}
			});
			repetido.set(0);
		});
		return new RespostaValidacao(this.getClass().getSimpleName(), config.getLimiteD().isValido(repetidoMaior.get()), repetidoMaior.get());
	}

	public void carregarListaEmMemoria(List<MS> list) {
		list.stream().forEach(ms -> criarTipoListaB(ms.getIdconcurso(),
				ms.getMegasenanumeroCollection().stream().map(Megasenanumero::getNumero).collect(Collectors.toList()),
				LISTA_D));
//		LOGGER.debug("Lista D criada");
	}

	private void criarTipoListaB(Integer idConcurso, List<Numero> list, List<JGDerivadoValidacao> listaRet) {

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
	private void criarSubListD(Integer concurso, List<Numero> list, List<JGDerivadoValidacao> listaRet) {
		JGDerivadoValidacao jgDerivadoValidacao = new JGDerivadoValidacao();
		jgDerivadoValidacao.setConcurso(concurso);
		jgDerivadoValidacao.setNumeros(list.stream().map(m -> m.clone()).collect(Collectors.toList()));
		listaRet.add(jgDerivadoValidacao);
	}

	public Map<String, Long> frequencia() {
		Map<String, Long> collect = LISTA_D.stream()
				.collect(Collectors.groupingBy(p -> p.getNum(), Collectors.counting()));
		return collect.entrySet().stream()
				.sorted((e2, e1) -> Long.compare(e1.getValue().longValue(), e2.getValue().longValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

	public RespostaValidacao validar(Ppt ppt) {
		return this.validar(ppt.getConfiguracoesCollection().stream().findFirst().orElse(new ConfiguracoesDTO()),
				ppt);
	}
}
