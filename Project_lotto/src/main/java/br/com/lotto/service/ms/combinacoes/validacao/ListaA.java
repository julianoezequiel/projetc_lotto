package br.com.lotto.service.ms.combinacoes.validacao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lotto.dao.MSRepository;
import br.com.lotto.dto.ConfiguracoesDTO;
import br.com.lotto.dto.PalpiteDTO;
import br.com.lotto.dto.RespostaValidacao;
import br.com.lotto.entity.MS;
import br.com.lotto.service.Validacao;
import br.com.lotto.service.ms.JGDerivadoValidacao;

@Service
public class ListaA implements Validacao {

	public static List<JGDerivadoValidacao> ListaA = new ArrayList<>();

	@Autowired
	private MSRepository msRepository;

	@Override
	public RespostaValidacao validar(ConfiguracoesDTO config, PalpiteDTO palpiteDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void carregarListaEmMemoria(List<MS> list) {		
		ListaA = list.stream().map(m -> {
			return criarTipoListaA(m);
		}).collect(Collectors.toList());
		System.out.println("Lista A carregada");
	}

	private JGDerivadoValidacao criarTipoListaA(MS ms) {
		JGDerivadoValidacao jgDerivadoValidacao = new JGDerivadoValidacao();
		jgDerivadoValidacao.setConcurso(ms.getConcurso());
		jgDerivadoValidacao.setNumerosFromMs(ms.getMegasenanumeroCollection());
		return jgDerivadoValidacao;
	}

}
