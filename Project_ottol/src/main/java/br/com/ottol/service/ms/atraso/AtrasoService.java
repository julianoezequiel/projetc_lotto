package br.com.ottol.service.ms.atraso;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ottol.dto.AtrasoDTO;
import br.com.ottol.dto.ConfiguracoesDTO;
import br.com.ottol.service.ms.MSService;
import br.com.ottol.service.ms.atraso.analise.AnaliseAtraso;
import br.com.ottol.utils.CONSTANTES.PARAM;

@Service
public class AtrasoService {

	@Autowired
	private AnaliseAtraso analiseAtraso;
	@Autowired
	private MSService msService;

	/**
	 * Calcula os atrazos das dezenas noc concursos realizados
	 *
	 * @return
	 */
	public Collection<AtrasoDTO> buscarAtrasos(Integer maxConc, Integer num) {
		return this.analiseAtraso.buscarAtrasos(maxConc.longValue(), num);
	}

	public Collection<AtrasoDTO> buscarAtrasos() {
		return this.analiseAtraso.buscarAtrasos(this.msService.total(), 0);
	}

	public Collection<AtrasoDTO> buscarAtrasosNum(Integer num) {
		return this.analiseAtraso.buscarAtrasos(this.msService.total(), num);
	}

	public HashMap<Object, Object> analisarAtraso() {
		HashMap<PARAM, Object> params = new HashMap<>();
		Integer inicio = (int) this.msService.total();
		params.put(PARAM.PARAM_INICIO, inicio);
		params.put(PARAM.PARAM_NUMERO, 0);
		return this.analiseAtraso.init(params);
	}
}
