package br.com.ottol.service.ms.combinacoes.validacao;

import java.util.ArrayList;
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
import br.com.ottol.dto.PalpiteDTO;
import br.com.ottol.dto.RespostaValidacao;
import br.com.ottol.entity.MS;
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
		// TODO Auto-generated method stub
		return null;
	}

	public void carregarListaEmMemoria(List<MS> list) {
		list.stream().forEach(ms -> criarTipoListaB(ms));
		LOGGER.debug("Lista E criada");
	}

	private void criarTipoListaB(MS ms) {

		AtomicInteger posisao = new AtomicInteger(1);

		// adiciona as posições corretas
		List<Numero> list = ms.getMegasenanumeroCollection().stream().map(m -> {
			m.getNumero().setPosisao(DEZ.fromInt(posisao.getAndIncrement()));
			return m.getNumero();
		}).collect(Collectors.toList());

		// compara os itens dos mapas do tipo com a lista de resultado
		map.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(f -> {
			criarSubListD(ms.getConcurso(),
					list.stream().filter(n -> f.getValue().contains(n.getPosisao())).collect(Collectors.toList()));
		});

	}

	/**
	 * Cria a lista
	 * 
	 * @param concurso
	 * @param list
	 */
	private void criarSubListD(Integer concurso, List<Numero> list) {
		JGDerivadoValidacao jgDerivadoValidacao = new JGDerivadoValidacao();
		jgDerivadoValidacao.setConcurso(concurso);
		jgDerivadoValidacao.setNumeros(list.stream().map(m -> m.clone()).collect(Collectors.toList()));
		LISTA_E.add(jgDerivadoValidacao);
	}
	

	public Map<String, Long> frequencia() {
		Map<String, Long> collect = LISTA_E.stream()
				.collect(Collectors.groupingBy(p -> p.getNum(), Collectors.counting()));
		return collect.entrySet().stream()
				.sorted((e2, e1) -> Long.compare(e1.getValue().longValue(), e2.getValue().longValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}


}
