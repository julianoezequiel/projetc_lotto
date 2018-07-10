package br.com.ottol.service.ms.combinacoes.analise;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ottol.dao.MSRepository;
import br.com.ottol.entity.MS;
import br.com.ottol.entity.Numero;
import br.com.ottol.service.Analise;
import br.com.ottol.service.ms.combinacoes.CombinacoesServices;

@Service
public class AnaliseCombinacoes implements Analise{
	
	public final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AnaliseCombinacoes.class.getName());
	
	@Autowired
	private CombinacoesServices combinacoesServices;
	@Autowired
    private MSRepository msRepository;
	
	@Override
	public void init(HashMap<Object, Object> params) {
		 this.combinacoesServices.limparListas();		 
		 List<MS> list = this.msRepository.findAll();
		 this.combinacoesServices.carregarLista(list);
		 analiseFrequenciasCombinacoes();
	}
	
	private void analiseFrequenciasCombinacoes(){
		Map<String, Map<String, Long>> map = this.combinacoesServices.frequencias();
		LOGGER.debug("Frequencias");
	}

}
