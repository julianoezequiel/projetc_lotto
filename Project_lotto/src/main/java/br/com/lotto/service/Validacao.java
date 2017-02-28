package br.com.lotto.service;

import br.com.lotto.dto.ConfiguracoesDTO;
import br.com.lotto.dto.PalpiteDTO;
import br.com.lotto.dto.RespostaValidacao;

public interface Validacao {

	public RespostaValidacao validar(ConfiguracoesDTO config,PalpiteDTO palpiteDTO);
}
