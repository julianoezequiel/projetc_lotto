package br.com.ot.service.ms.comb.analise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import br.com.ot.dao.MSRepository;
import br.com.ot.entity.MS;
import br.com.ot.service.Analise;
import br.com.ot.service.ms.comb.CombinacoesServices;
import br.com.ot.utils.CONSTANTES.PARAM;

@Service
@Scope(value="prototype", proxyMode=ScopedProxyMode.TARGET_CLASS) 
public class AnaliseCombinacoes implements Analise {

	public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AnaliseCombinacoes.class.getName());

	@Autowired
	private CombinacoesServices combinacoesServices;
	@Autowired
	private MSRepository msRepository;

	@Override
	public HashMap<Object, Object> init(HashMap<PARAM, Object> params) {
		HashMap<Object, Object> map = new HashMap<>();
		List<MS> list = this.msRepository.findAll();
		this.combinacoesServices.carregarLista(list);
		Map<String, Map<String, Long>> result = analiseFrequenciasCombinacoes(list);
		map.put("RESULT", result);
		return map;
	}

	private Map<String, Map<String, Long>> analiseFrequenciasCombinacoes(List<MS> list) {
		Map<String, Map<String, Long>> map = this.combinacoesServices.frequencias(list);
		LOGGER.debug("Frequencias");
		return map;
	}

	public void analiseCompleta() {

	}
}
