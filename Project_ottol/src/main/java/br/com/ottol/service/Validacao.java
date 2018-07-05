package br.com.ottol.service;

import br.com.ottol.dto.ConfiguracoesDTO;
import br.com.ottol.dto.PalpiteDTO;
import br.com.ottol.dto.RespostaValidacao;

public interface Validacao {

	public RespostaValidacao validar(ConfiguracoesDTO config,PalpiteDTO palpiteDTO);
}
