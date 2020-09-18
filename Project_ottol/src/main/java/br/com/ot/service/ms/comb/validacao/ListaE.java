package br.com.ot.service.ms.comb.validacao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import br.com.ot.dao.MSRepository;
import br.com.ot.dto.ConfiguracoesDTO;
import br.com.ot.dto.NumeroDTO;
import br.com.ot.dto.Ppt;
import br.com.ot.dto.RespostaValidacao;
import br.com.ot.entity.MS;
import br.com.ot.entity.Megasenanumero;
import br.com.ot.entity.Numero;
import br.com.ot.service.Validacao;
import br.com.ot.service.ms.JGDerivadoValidacao;
import br.com.ot.utils.DEZ;
import br.com.ot.utils.Utils;

@Component
@Scope(value="prototype", proxyMode=ScopedProxyMode.TARGET_CLASS) 
public class ListaE implements Validacao {
	public final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ListaE.class.getName());

	public static HashMap<String, List<DEZ>> map;

	public ListaE() {
		ListaE.map = Utils.MONTAR_LISTA_E();
	}

	@Autowired
	private MSRepository msRepository;

	@Override
	public RespostaValidacao validar(ConfiguracoesDTO config, Ppt ppt, List<JGDerivadoValidacao> LISTA_E) {
		LOGGER.debug("LISTA E");
		if (ppt.getConfiguracoesDTO().getLimiteE().getMax() == -1) {
			return new RespostaValidacao(this.getClass().getSimpleName(), true, true);
		} else {
			List<JGDerivadoValidacao> listaRet = new ArrayList<>();
			List<Numero> collect = ppt.getNumeroCollection().stream()
					.sorted(Comparator.comparing(NumeroDTO::getIdNumero)).map(m -> new Numero(m.getIdNumero()))
					.collect(Collectors.toList());

			criarTipoListaB(ppt.getMegasenaidconcurso().getIdconcurso(), new ArrayList<>(collect), listaRet);
			ArrayList<JGDerivadoValidacao> listaE = new ArrayList<>(LISTA_E);
			AtomicInteger repetido = new AtomicInteger(0);
			AtomicInteger repetidoMaior = new AtomicInteger(0);
			listaRet.stream().forEach(lt -> {
				listaE.stream().forEach(pe -> {
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
			return new RespostaValidacao(this.getClass().getSimpleName(),
					config.getLimiteE().isValido(repetidoMaior.get()), repetidoMaior.get());
		}
	}

	public List<JGDerivadoValidacao> carregarListaEmMemoria(List<MS> list) {
		List<JGDerivadoValidacao> LISTA_E = new ArrayList<JGDerivadoValidacao>();
		list.stream().forEach(ms -> criarTipoListaB(ms.getIdconcurso(),
				ms.getMegasenanumeroCollection().stream().map(Megasenanumero::getNumero).collect(Collectors.toList()),
				LISTA_E));
		return LISTA_E;
	}

	private synchronized void criarTipoListaB(Integer idConcurso, List<Numero> list,
			List<JGDerivadoValidacao> listaRet) {

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

	public Map<String, Long> frequencia(List<JGDerivadoValidacao> LISTA_E) {
		Map<String, Long> collect = LISTA_E.stream()
				.collect(Collectors.groupingBy(p -> p.getNum(), Collectors.counting()));
		return collect.entrySet().stream()
				.sorted((e2, e1) -> Long.compare(e1.getValue().longValue(), e2.getValue().longValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

	public RespostaValidacao validar(Ppt ppt, List<JGDerivadoValidacao> LISTA_E) {
		return this.validar(ppt.getConfiguracoesDTO(), ppt, LISTA_E);
	}

}
