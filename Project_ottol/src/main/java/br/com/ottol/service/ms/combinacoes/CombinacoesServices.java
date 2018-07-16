package br.com.ottol.service.ms.combinacoes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ottol.entity.MS;
import br.com.ottol.service.ms.combinacoes.analise.AnaliseCombinacoes;
import br.com.ottol.service.ms.combinacoes.validacao.ListaA;
import br.com.ottol.service.ms.combinacoes.validacao.ListaB;
import br.com.ottol.service.ms.combinacoes.validacao.ListaC;
import br.com.ottol.service.ms.combinacoes.validacao.ListaD;
import br.com.ottol.service.ms.combinacoes.validacao.ListaE;
import br.com.ottol.utils.CONSTANTES.PARAM;

@Service
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

	public void carregarLista(List<MS> list) {
		this.listaA.carregarListaEmMemoria(list);
		this.listaB.carregarListaEmMemoria(list);
		this.listaC.carregarListaEmMemoria(list);
		this.listaD.carregarListaEmMemoria(list);
		this.listaE.carregarListaEmMemoria(list);
	}

	public void limparListas() {
		ListaA.LISTA_A.clear();
		ListaB.LISTA_B.clear();
		ListaC.LISTA_C.clear();
		ListaD.LISTA_D.clear();
		ListaE.LISTA_E.clear();
	}

	public Map<String, Map<String, Long>> frequencias() {
		HashMap<String, Map<String, Long>> map = new HashMap<>();
		map.put("A", this.listaA.frequencia());
		map.put("B", this.listaB.frequencia());
		map.put("C", this.listaC.frequencia());
		map.put("D", this.listaD.frequencia());
		map.put("E", this.listaE.frequencia());
		return map;
	}

	public HashMap<Object, Object> analiseCombinacoes() {
		HashMap<PARAM, Object> params = new HashMap<>();
		return this.analiseCombinacoes.init(params);
	}

}
