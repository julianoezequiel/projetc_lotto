package br.com.ottol.service.ms.combinacoes.validacao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ottol.dao.MSRepository;
import br.com.ottol.dto.ConfiguracoesDTO;
import br.com.ottol.dto.PalpiteDTO;
import br.com.ottol.dto.RespostaValidacao;
import br.com.ottol.entity.MS;
import br.com.ottol.service.Validacao;
import br.com.ottol.service.ms.JGDerivadoValidacao;
	
@Component
public class ListaA implements Validacao {
	public final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ListaA.class.getName());
	public static List<JGDerivadoValidacao> LISTA_A = new ArrayList<>();

	@Autowired
	private MSRepository msRepository;

	@Override
	public RespostaValidacao validar(ConfiguracoesDTO config, PalpiteDTO palpiteDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void carregarListaEmMemoria(List<MS> list) {		
		LISTA_A = list.stream().map(m -> {
			return criarTipoListaA(m);
		}).collect(Collectors.toList());
		LOGGER.debug("Lista A criada");
	}

	private JGDerivadoValidacao criarTipoListaA(MS ms) {
		JGDerivadoValidacao jgDerivadoValidacao = new JGDerivadoValidacao();
		jgDerivadoValidacao.setConcurso(ms.getConcurso());
		jgDerivadoValidacao.setNumerosFromMs(ms.getMegasenanumeroCollection());
		return jgDerivadoValidacao;
	}

}
