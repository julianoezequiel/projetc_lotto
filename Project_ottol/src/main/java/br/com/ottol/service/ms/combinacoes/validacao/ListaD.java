package br.com.ottol.service.ms.combinacoes.validacao;

import java.util.ArrayList;
import java.util.List;
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

@Component
public class ListaD implements Validacao {

	public static List<JGDerivadoValidacao> ListaD = new ArrayList<>();

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

		List<Numero> list = ms.getMegasenanumeroCollection().stream().map(m -> m.getNumero())
				.collect(Collectors.toList());

		int index1 = list.size() - 1;
		int index2 = index1 - 1;
		int index3 = index2 - 1;

		while (index1 != 1 || index2 != 0 || index3 != 0) {

			Numero n1 = list.get(index1);
			Numero n2 = list.get(index2);
			Numero n3 = list.get(index3);

			criarSubListD(ms.getConcurso(), list.stream().map(m -> m.clone()).collect(Collectors.toList()), n1, n2, n3);

			if (index3 == 0) {
				index2--;
				index3 = index2 - 1;
			} else if (index2 == 0) {
				index1--;
				index2 = index1 - 1;
			} else {
				index3--;
			}

		}
	}

	private void criarSubListD(Integer concurso, List<Numero> list, Numero n1, Numero n2, Numero n3) {
		JGDerivadoValidacao jgDerivadoValidacao = new JGDerivadoValidacao();
		jgDerivadoValidacao.setConcurso(concurso);
		list.remove(n1);
		list.remove(n2);
		list.remove(n3);
		jgDerivadoValidacao.setNumeros(list);
		ListaD.add(jgDerivadoValidacao);
	}

}
