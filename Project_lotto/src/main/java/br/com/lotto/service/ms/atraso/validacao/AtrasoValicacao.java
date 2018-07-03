package br.com.lotto.service.ms.atraso.validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lotto.dto.ConfiguracoesDTO;
import br.com.lotto.dto.PalpiteDTO;
import br.com.lotto.dto.RespostaValidacao;
import br.com.lotto.service.Validacao;
import br.com.lotto.service.ms.atraso.analise.AnaliseAtraso;

@Service
public class AtrasoValicacao implements Validacao {

	@Autowired
	private AnaliseAtraso atrazoAnalizeService;

	@Override
	public RespostaValidacao validar(ConfiguracoesDTO config, PalpiteDTO palpiteDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
