package br.com.lotto.service.ms.combinacoes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lotto.entity.MS;
import br.com.lotto.service.ms.combinacoes.validacao.ListaA;
import br.com.lotto.service.ms.combinacoes.validacao.ListaB;
import br.com.lotto.service.ms.combinacoes.validacao.ListaC;
import br.com.lotto.service.ms.combinacoes.validacao.ListaD;

@Service
public class CombinacoesServices {

	@Autowired
	private ListaA listaA;
	@Autowired
	private ListaB listaB;
	@Autowired
	private ListaC listaC;
	@Autowired
	private ListaD listaD;
	
	public void carregarLista(List<MS> list){
		this.listaA.carregarListaEmMemoria(list);
		this.listaB.carregarListaEmMemoria(list);
		this.listaC.carregarListaEmMemoria(list);
		this.listaD.carregarListaEmMemoria(list);
	}
	
}
