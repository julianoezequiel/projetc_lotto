package br.com.ottol.service.ms.combinacoes.analise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ottol.dao.MSRepository;
import br.com.ottol.entity.MS;
import br.com.ottol.service.Analise;
import br.com.ottol.service.ms.combinacoes.CombinacoesServices;
import br.com.ottol.utils.CONSTANTES.PARAM;

@Service
public class AnaliseCombinacoes implements Analise {

	public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AnaliseCombinacoes.class.getName());

	@Autowired
	private CombinacoesServices combinacoesServices;
	@Autowired
	private MSRepository msRepository;

	@Override
	public HashMap<Object, Object> init(HashMap<PARAM, Object> params) {
		HashMap<Object, Object> map = new HashMap<>();
		this.combinacoesServices.limparListas();
		List<MS> list = this.msRepository.findAll();
		this.combinacoesServices.carregarLista(list);
		Map<String, Map<String, Long>> result = analiseFrequenciasCombinacoes();
		map.put("RESULT", result);
		return map;
	}

	private Map<String, Map<String, Long>> analiseFrequenciasCombinacoes() {
		Map<String, Map<String, Long>> map = this.combinacoesServices.frequencias();
		LOGGER.debug("Frequencias");
		return map;
	}

}
