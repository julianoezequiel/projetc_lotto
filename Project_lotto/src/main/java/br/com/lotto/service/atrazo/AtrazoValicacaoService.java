package br.com.lotto.service.atrazo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lotto.dto.Configuracoes;
import br.com.lotto.dto.Jogos;
import br.com.lotto.dto.RespostaValidacao;
import br.com.lotto.service.Validacao;
import br.com.lotto.service.frequencia.analise.AnaliseAtrazo;

@Service
public class AtrazoValicacaoService implements Validacao{

	@Autowired
	private AnaliseAtrazo atrazoAnalizeService;
	
	@Override
	public RespostaValidacao validar(Configuracoes config, Jogos jogos) {
		// TODO Auto-generated method stub
		return null;
	}

}