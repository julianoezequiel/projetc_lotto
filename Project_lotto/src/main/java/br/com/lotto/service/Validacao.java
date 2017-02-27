package br.com.lotto.service;

import br.com.lotto.dto.Configuracoes;
import br.com.lotto.dto.Jogos;
import br.com.lotto.dto.RespostaValidacao;

public interface Validacao {

	public RespostaValidacao validar(Configuracoes config,Jogos jogos);
}
