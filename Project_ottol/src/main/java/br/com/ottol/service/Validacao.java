package br.com.ottol.service;

import br.com.ottol.dto.ConfiguracoesDTO;
import br.com.ottol.dto.Ppt;
import br.com.ottol.dto.RespostaValidacao;

public interface Validacao {

	public RespostaValidacao validar(ConfiguracoesDTO config,Ppt ppt);
}
