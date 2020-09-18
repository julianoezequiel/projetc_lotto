package br.com.ot.service.ms.atraso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ot.dto.AtrasoDTO;
import br.com.ot.dto.Ppt;
import br.com.ot.dto.RespostaValidacao;
import br.com.ot.entity.Numero;
import br.com.ot.service.ms.MSService;
import br.com.ot.service.ms.atraso.analise.AnaliseAtraso;
import br.com.ot.service.num.NumeroService;
import br.com.ot.utils.CONSTANTES;
import br.com.ot.utils.CONSTANTES.PARAM;

@Service
public class AtrasoService {

	@Autowired
	private AnaliseAtraso analiseAtraso;
	@Autowired
	private MSService msService;
	@Autowired
	private NumeroService numeroService;

	private Collection<AtrasoDTO> atrazadosRecursivo = null;

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

	public Collection<AtrasoDTO> buscarAtrasos(Long maxConc, Integer numeroFiltro, Collection<Numero> nums) {
		return this.analiseAtraso.buscarAtrasos(maxConc, 0, nums);
	}

	public synchronized List<RespostaValidacao> validar(Ppt ppt, Collection<AtrasoDTO> buscarAtrasos) {
		List<RespostaValidacao> validacaos = new ArrayList<>();
		validacaos.add(this.validarMaisAtrasado(ppt, buscarAtrasos));
		validacaos.add(this.validarMenosAtrasado(ppt, buscarAtrasos));
		return validacaos;
	}

	public RespostaValidacao validarCiclo(Ppt ppt, Collection<AtrasoDTO> buscarAtrasos) {
		if (ppt.getConfiguracoesDTO().getMediaCiclo() == -1) {
			return new RespostaValidacao(CONSTANTES.TIPO_VALIDACAO.CIC.name(), true, true);
		} else {
			List<AtrasoDTO> collect = buscarAtrasos.stream()
					.filter(f -> f.getMediaCiclo() >= ppt.getConfiguracoesDTO().getMediaCiclo())
					.collect(Collectors.toList());

			AtomicInteger contains = new AtomicInteger(0);

			ppt.getNumeroCollection().stream().forEach(f -> {
				collect.stream().forEach(ff -> {
					if (f.getIdNumero().equals(ff.getNumero())) {
						contains.getAndIncrement();
					}
				});
			});

			return new RespostaValidacao(CONSTANTES.TIPO_VALIDACAO.CIC.name(), contains.get() > 0, contains.get());
		}
	}

	public RespostaValidacao validarMenosAtrasado(Ppt ppt, Collection<AtrasoDTO> buscarAtrasos) {
		if (ppt.getConfiguracoesDTO().getMenosAtrazado() == -1) {
			return new RespostaValidacao(CONSTANTES.TIPO_VALIDACAO.ATLESS.name(), true, true);
		} else {
			List<AtrasoDTO> collect = buscarAtrasos.stream().sorted(Comparator.comparing(AtrasoDTO::getAtual))
					.limit(ppt.getConfiguracoesDTO().getMenosAtrazado()).collect(Collectors.toList());

			AtomicInteger contains = new AtomicInteger(0);

			ppt.getNumeroCollection().stream().forEach(f -> {
				collect.stream().forEach(ff -> {
					if (f.getIdNumero().equals(ff.getNumero())) {
						contains.getAndIncrement();
					}
				});
			});
			return new RespostaValidacao(CONSTANTES.TIPO_VALIDACAO.ATLESS.name(), contains.get() > 0, contains.get());
		}
	}

	public RespostaValidacao validarMaisAtrasado(Ppt ppt, Collection<AtrasoDTO> buscarAtrasos) {
		if (ppt.getConfiguracoesDTO().getMaisAtrazado() == -1) {
			return new RespostaValidacao(CONSTANTES.TIPO_VALIDACAO.ATPLUS.name(), true, true);
		} else {
			List<AtrasoDTO> collect = buscarAtrasos.stream()
					.sorted(Comparator.comparing(AtrasoDTO::getAtual).reversed())
					.limit(ppt.getConfiguracoesDTO().getMaisAtrazado()).collect(Collectors.toList());

			AtomicInteger contains = new AtomicInteger(0);

			ppt.getNumeroCollection().stream().forEach(f -> {
				collect.stream().forEach(ff -> {
					if (f.getIdNumero().equals(ff.getNumero())) {
						contains.getAndIncrement();
					}
				});
			});
			return new RespostaValidacao(CONSTANTES.TIPO_VALIDACAO.ATPLUS.name(), contains.get() > 0, contains.get());
		}
	}

}
