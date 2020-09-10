package br.com.ottol.service.ms.atraso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ottol.dto.AtrasoDTO;
import br.com.ottol.dto.ConfiguracoesDTO;
import br.com.ottol.dto.NumeroDTO;
import br.com.ottol.dto.Ppt;
import br.com.ottol.dto.RespostaValidacao;
import br.com.ottol.entity.Numero;
import br.com.ottol.service.ms.MSService;
import br.com.ottol.service.ms.atraso.analise.AnaliseAtraso;
import br.com.ottol.service.num.NumeroService;
import br.com.ottol.utils.CONSTANTES.PARAM;

@Service
public class AtrasoService {

	@Autowired
	private AnaliseAtraso analiseAtraso;
	@Autowired
	private MSService msService;
	@Autowired
	private NumeroService numeroService;

	/**
	 * Calcula os atrazos das dezenas noc concursos realizados
	 *
	 * @return
	 */
	public Collection<AtrasoDTO> buscarAtrasos(Integer maxConc, Integer num) {
		Collection<Numero> buscarTodos = this.numeroService.buscarTodos();
		return this.analiseAtraso.buscarAtrasos(maxConc.longValue(), num, buscarTodos);
	}

	public Collection<AtrasoDTO> buscarAtrasos() {
		Collection<Numero> buscarTodos = this.numeroService.buscarTodos();
		return this.analiseAtraso.buscarAtrasos(this.msService.total(), 0, buscarTodos);
	}

	public Collection<AtrasoDTO> buscarAtrasosNum(Integer num) {
		Collection<Numero> buscarTodos = this.numeroService.buscarTodos();
		return this.analiseAtraso.buscarAtrasos(this.msService.total(), num, buscarTodos);
	}

	public HashMap<Object, Object> analisarAtraso() {
		HashMap<Object, Object> map = new HashMap<>();
		HashMap<PARAM, Object> params = new HashMap<>();
		Integer inicio = (int) this.msService.total();
		params.put(PARAM.PARAM_INICIO, inicio);
		params.put(PARAM.PARAM_NUMERO, 0);
//		return this.analiseAtraso.init(params);
		map.put(PARAM.RESULT, this.analiseAtraso.buscarAtrasosAvancado());
		return map;
	}

	public Collection<AtrasoDTO> analizarRecursivo(Ppt ppt) {
		return this.analiseAtraso.analizarRecursivo(ppt);
	}

	public synchronized List<RespostaValidacao> validar(Ppt ppt) {
		Collection<Numero> buscarTodos = this.numeroService.buscarTodos();
		Collection<AtrasoDTO> buscarAtrasos = this.analiseAtraso.buscarAtrasos(this.msService.total(), 0, buscarTodos);
		List<RespostaValidacao> validacaos = new ArrayList<>();
		validacaos.add(this.validarMaisAtrasado(ppt, buscarTodos, buscarAtrasos));
		validacaos.add(this.validarMenosAtrasado(ppt, buscarTodos, buscarAtrasos));
		validacaos.add(this.validarCiclo(ppt, buscarTodos, buscarAtrasos));
		return validacaos;
	}

	private RespostaValidacao validarCiclo(Ppt ppt, Collection<Numero> buscarTodos,
			Collection<AtrasoDTO> buscarAtrasos) {
		return new RespostaValidacao("CICLO", true, 0);
	}

	private RespostaValidacao validarMenosAtrasado(Ppt ppt, Collection<Numero> buscarTodos,
			Collection<AtrasoDTO> buscarAtrasos) {
		return new RespostaValidacao("VMA-", true, 0);
	}

	private RespostaValidacao validarMaisAtrasado(Ppt ppt, Collection<Numero> buscarTodos,
			Collection<AtrasoDTO> buscarAtrasos) {
		List<AtrasoDTO> collect = buscarAtrasos.stream().filter(f -> f.getAtual() >= ppt.getConfig().getMaisAtrazado())
				.collect(Collectors.toList());

		AtomicInteger contains = new AtomicInteger(0);

		ppt.getNumeroCollection().stream().forEach(f -> {
			collect.stream().forEach(ff -> {
				if (f.getIdNumero().equals(ff.getNumero())) {
					contains.getAndIncrement();
				}
			});
		});
		return new RespostaValidacao("VMA+", contains.get() > 0, contains.get());
	}

}
