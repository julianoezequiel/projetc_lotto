package br.com.ottol.service.ms.combinacoes.validacao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
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
	public RespostaValidacao validar(ConfiguracoesDTO config, PalpiteDTO palpiteDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	public void carregarListaEmMemoria(List<MS> list) {
		list.stream().forEach(ms -> criarTipoListaB(ms));
		LOGGER.debug("Lista D criada");
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
		LISTA_D.add(jgDerivadoValidacao);
	}

}
