package br.com.lotto.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lotto.dao.NumeroRepository;
import br.com.lotto.entity.Numero;

@Service
public class NumeroService {

	
	@Autowired
	private NumeroRepository numeroRepository;
	
	public Collection<Numero> buscarTodos(){
		return this.numeroRepository.findAll();
	}
}
