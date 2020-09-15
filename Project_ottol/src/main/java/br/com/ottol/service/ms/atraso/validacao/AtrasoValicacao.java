package br.com.ottol.service.ms.atraso.validacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ottol.dto.ConfiguracoesDTO;
import br.com.ottol.dto.Ppt;
import br.com.ottol.dto.RespostaValidacao;
import br.com.ottol.service.Validacao;
import br.com.ottol.service.ms.JGDerivadoValidacao;
import br.com.ottol.service.ms.atraso.analise.AnaliseAtraso;

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
