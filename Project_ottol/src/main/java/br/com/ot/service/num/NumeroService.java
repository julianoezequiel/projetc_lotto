package br.com.ot.service.num;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ot.dao.NumeroRepository;
import br.com.ot.entity.Numero;

@Service
public class NumeroService {
	
	@Autowired
	private NumeroRepository numeroRepository;
	
	public Collection<Numero> buscarTodos(){
		return this.numeroRepository.findAll();
	}
	
}
