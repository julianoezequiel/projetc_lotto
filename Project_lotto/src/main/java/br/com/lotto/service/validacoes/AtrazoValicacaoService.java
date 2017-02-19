package br.com.lotto.service.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lotto.dto.Configuracoes;
import br.com.lotto.dto.Jogos;
import br.com.lotto.dto.RespostaValidacao;
import br.com.lotto.service.analize.AtrazoAnalizeService;

@Service
public class AtrazoValicacaoService implements Validacao{

	@Autowired
	private AtrazoAnalizeService atrazoAnalizeService;
	
	@Override
	public RespostaValidacao validar(Configuracoes config, Jogos jogos) {
		// TODO Auto-generated method stub
		return null;
	}

}