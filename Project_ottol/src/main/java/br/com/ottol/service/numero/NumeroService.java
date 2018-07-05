package br.com.ottol.service.numero;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ottol.dao.NumeroRepository;
import br.com.ottol.entity.Numero;

@Service
public class NumeroService {

	
	@Autowired
	private NumeroRepository numeroRepository;
	
	public Collection<Numero> buscarTodos(){
		return this.numeroRepository.findAll();
	}
}
