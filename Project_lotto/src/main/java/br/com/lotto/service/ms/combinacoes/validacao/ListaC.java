package br.com.lotto.service.ms.combinacoes.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.lotto.dao.MSRepository;
import br.com.lotto.dto.ConfiguracoesDTO;
import br.com.lotto.dto.PalpiteDTO;
import br.com.lotto.dto.RespostaValidacao;
import br.com.lotto.entity.MS;
import br.com.lotto.service.Validacao;
import br.com.lotto.service.ms.JGDerivadoValidacao;

public class ListaC implements Validacao{
	
	public static List<JGDerivadoValidacao> ListaC = new ArrayList<>();

	@Autowired
	private MSRepository msRepository;
	
	@Override
	public RespostaValidacao validar(ConfiguracoesDTO config, PalpiteDTO palpiteDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	public void carregarListaEmMemoria(List<MS> list) {
		// TODO Auto-generated method stub
		
	}

}
