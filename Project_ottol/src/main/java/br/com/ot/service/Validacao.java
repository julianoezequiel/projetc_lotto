package br.com.ot.service;

import java.util.List;

import br.com.ot.dto.ConfiguracoesDTO;
import br.com.ot.dto.Ppt;
import br.com.ot.dto.RespostaValidacao;
import br.com.ot.entity.MS;
import br.com.ot.service.ms.JGDerivadoValidacao;

public interface Validacao {

	public RespostaValidacao validar(ConfiguracoesDTO config,Ppt ppt,List<JGDerivadoValidacao> list);
}
