package br.com.ottol.service.ms.combinacoes.validacao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ottol.dao.MSRepository;
import br.com.ottol.dto.ConfiguracoesDTO;
import br.com.ottol.dto.PalpiteDTO;
import br.com.ottol.dto.RespostaValidacao;
import br.com.ottol.entity.MS;
import br.com.ottol.entity.Megasenanumero;
import br.com.ottol.entity.Numero;
import br.com.ottol.service.Validacao;
import br.com.ottol.service.ms.JGDerivadoValidacao;

@Component
public class ListaB implements Validacao {

	public static List<JGDerivadoValidacao> LISTA_B = new ArrayList<>();

	@Autowired
	private MSRepository msRepository;

	@Override
	public RespostaValidacao validar(ConfiguracoesDTO config, PalpiteDTO palpiteDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	public void carregarListaEmMemoria(List<MS> list) {
		list.stream().forEach(ms -> criarTipoListaB(ms));
	}

	private void criarTipoListaB(MS ms) {

		List<Numero> list = ms.getMegasenanumeroCollection().stream().map(m -> m.getNumero())
				.collect(Collectors.toList());

		list.stream().forEach(n -> criarSubListB1(ms.getConcurso(),
				list.stream().map(m -> m.clone()).collect(Collectors.toList()), n));

	}

	private void criarSubListB1(Integer concurso, List<Numero> list, Numero n) {
		JGDerivadoValidacao jgDerivadoValidacao = new JGDerivadoValidacao();
		jgDerivadoValidacao.setConcurso(concurso);
		list.remove(n);
		jgDerivadoValidacao.setNumeros(list);
		LISTA_B.add(jgDerivadoValidacao);
	}

}
