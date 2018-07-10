package br.com.ottol.service.ms.combinacoes.validacao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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
		System.out.println("Lista c criada");
	}

	private void criarTipoListaB(MS ms) {

		AtomicInteger posisao = new AtomicInteger(1);

		List<Numero> list = ms.getMegasenanumeroCollection().stream().map(m -> {
			m.getNumero().setPosisao(DEZ.fromInt(posisao.getAndIncrement()));
			return m.getNumero();
		}).collect(Collectors.toList());

		map.entrySet().stream().forEach(f -> {
			criarSubListD(ms.getConcurso(),
					list.stream().filter(n -> f.getValue().contains(n.getPosisao())).collect(Collectors.toList()));
		});

	}

	private void criarSubListD(Integer concurso, List<Numero> list) {
		JGDerivadoValidacao jgDerivadoValidacao = new JGDerivadoValidacao();
		jgDerivadoValidacao.setConcurso(concurso);
		jgDerivadoValidacao.setNumeros(list);
		LISTA_D.add(jgDerivadoValidacao);
	}

}
