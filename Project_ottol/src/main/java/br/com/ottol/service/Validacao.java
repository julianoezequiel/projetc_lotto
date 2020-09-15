package br.com.ottol.service;

import java.util.List;

import br.com.ottol.dto.ConfiguracoesDTO;
import br.com.ottol.dto.Ppt;
import br.com.ottol.dto.RespostaValidacao;
import br.com.ottol.entity.MS;
import br.com.ottol.service.ms.JGDerivadoValidacao;

public interface Validacao {

	public RespostaValidacao validar(ConfiguracoesDTO config,Ppt ppt,List<JGDerivadoValidacao> list);
}
