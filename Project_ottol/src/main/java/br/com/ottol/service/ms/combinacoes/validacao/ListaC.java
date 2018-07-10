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
public class ListaC implements Validacao {

	public static List<JGDerivadoValidacao> LISTA_C = new ArrayList<>();

	@Autowired
	private MSRepository msRepository;

	@Override
	public RespostaValidacao validar(ConfiguracoesDTO config, PalpiteDTO palpiteDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	public void carregarListaEmMemoria(List<MS> list) {
		list.stream().forEach(ms -> criarTipoListaC(ms));
		System.out.println("Lista c criada");
	}

	private void criarTipoListaC(MS ms) {

		List<Numero> list = ms.getMegasenanumeroCollection().stream().map(m -> m.getNumero())
				.collect(Collectors.toList());

		int index1 = list.size() - 1;
		int index2 = index1 - 1;

		while (index1 != 1 || index2 != 0) {

			Numero n1 = list.get(index1);
			Numero n2 = list.get(index2);

			criarSubListB1(ms.getConcurso(), list.stream()
					.map(m -> m.clone())
					.collect(Collectors.toList()), n1, n2);

			if (index2 == 0) {
				index1--;
				index2 = index1 - 1;
			} else {
				index2--;
			}

		}
	}

	private void criarSubListB1(Integer concurso, List<Numero> list, Numero n1, Numero n2) {
		JGDerivadoValidacao jgDerivadoValidacao = new JGDerivadoValidacao();
		jgDerivadoValidacao.setConcurso(concurso);
		list.remove(n1);
		list.remove(n2);
		jgDerivadoValidacao.setNumeros(list);
		LISTA_C.add(jgDerivadoValidacao);
	}

}
