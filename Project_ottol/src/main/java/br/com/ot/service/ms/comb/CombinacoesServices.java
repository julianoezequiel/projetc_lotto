package br.com.ot.service.ms.comb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import br.com.ot.dto.Ppt;
import br.com.ot.dto.RespostaValidacao;
import br.com.ot.entity.MS;
import br.com.ot.service.ms.JGDerivadoValidacao;
import br.com.ot.service.ms.comb.analise.AnaliseCombinacoes;
import br.com.ot.service.ms.comb.validacao.ListaA;
import br.com.ot.service.ms.comb.validacao.ListaB;
import br.com.ot.service.ms.comb.validacao.ListaC;
import br.com.ot.service.ms.comb.validacao.ListaD;
import br.com.ot.service.ms.comb.validacao.ListaE;
import br.com.ot.utils.CONSTANTES.PARAM;

@Service
@Scope(value="prototype", proxyMode=ScopedProxyMode.TARGET_CLASS) 
public class CombinacoesServices {

	@Autowired
	private AnaliseCombinacoes analiseCombinacoes;
	@Autowired
	private ListaA listaA;
	@Autowired
	private ListaB listaB;
	@Autowired
	private ListaC listaC;
	@Autowired
	private ListaD listaD;
	@Autowired
	private ListaE listaE;

	public Map<String, List<JGDerivadoValidacao>> carregarLista(List<MS> list) {
		HashMap<String, List<JGDerivadoValidacao>> map = new HashMap<>();
		map.put(ListaA.class.getSimpleName(), this.listaA.carregarListaEmMemoria(list));
		map.put(ListaB.class.getSimpleName(), this.listaB.carregarListaEmMemoria(list));
		map.put(ListaC.class.getSimpleName(), this.listaC.carregarListaEmMemoria(list));
		map.put(ListaD.class.getSimpleName(), this.listaD.carregarListaEmMemoria(list));
		map.put(ListaE.class.getSimpleName(), this.listaE.carregarListaEmMemoria(list));
		return map;
	}


	public Map<String, Map<String, Long>> frequencias(List<MS> list) {
		Map<String, List<JGDerivadoValidacao>> hashMap = this.carregarLista(list);
		HashMap<String, Map<String, Long>> map = new HashMap<>();
		map.put("A", this.listaA.frequencia(hashMap.get(ListaA.class.getSimpleName())));
		map.put("B", this.listaB.frequencia(hashMap.get(ListaB.class.getSimpleName())));
		map.put("C", this.listaC.frequencia(hashMap.get(ListaC.class.getSimpleName())));
		map.put("D", this.listaD.frequencia(hashMap.get(ListaD.class.getSimpleName())));
		map.put("E", this.listaE.frequencia(hashMap.get(ListaE.class.getSimpleName())));
		return map;
	}

	public Map<Object, Object> analiseCombinacoes() {
		HashMap<PARAM, Object> params = new HashMap<>();
		return this.analiseCombinacoes.init(params);
	}

	public synchronized List<RespostaValidacao> validar(Ppt ppt, Map<String, List<JGDerivadoValidacao>> hashMap) {
		List<RespostaValidacao> validacaos = new ArrayList<>();
		validacaos.add(this.listaA.validar(ppt, hashMap.get(ListaA.class.getSimpleName())));
		validacaos.add(this.listaB.validar(ppt, hashMap.get(ListaB.class.getSimpleName())));
		validacaos.add(this.listaC.validar(ppt, hashMap.get(ListaC.class.getSimpleName())));
		validacaos.add(this.listaD.validar(ppt, hashMap.get(ListaD.class.getSimpleName())));
		validacaos.add(this.listaE.validar(ppt, hashMap.get(ListaE.class.getSimpleName())));
		return validacaos;
	}

	public RespostaValidacao validarListaA(Ppt ppt, List<JGDerivadoValidacao> list) {
		return this.listaA.validar(ppt.getConfiguracoesDTO(), ppt, list);
	}

	public RespostaValidacao validarListaB(Ppt ppt, List<JGDerivadoValidacao> list) {
		return this.listaB.validar(ppt.getConfiguracoesDTO(), ppt, list);
	}

	public RespostaValidacao validarListaC(Ppt ppt, List<JGDerivadoValidacao> list) {
		return this.listaC.validar(ppt.getConfiguracoesDTO(), ppt, list);
	}

	public RespostaValidacao validarListaD(Ppt ppt, List<JGDerivadoValidacao> list) {
		return this.listaD.validar(ppt.getConfiguracoesDTO(), ppt, list);
	}

	public RespostaValidacao validarListaE(Ppt ppt, List<JGDerivadoValidacao> list) {
		return this.listaE.validar(ppt.getConfiguracoesDTO(), ppt, list);
	}

}
