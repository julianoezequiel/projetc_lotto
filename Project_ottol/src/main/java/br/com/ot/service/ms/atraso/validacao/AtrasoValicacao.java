package br.com.ot.service.ms.atraso.validacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ot.dto.ConfiguracoesDTO;
import br.com.ot.dto.Ppt;
import br.com.ot.dto.RespostaValidacao;
import br.com.ot.service.Validacao;
import br.com.ot.service.ms.JGDerivadoValidacao;
import br.com.ot.service.ms.atraso.analise.AnaliseAtraso;

@Service
public class AtrasoValicacao implements Validacao {

	@Autowired
	private AnaliseAtraso atrazoAnalizeService;

	@Override
	public RespostaValidacao validar(ConfiguracoesDTO config, Ppt ppt,List<JGDerivadoValidacao> list) {
		// TODO Auto-generated method stub
		return null;
	}

}
