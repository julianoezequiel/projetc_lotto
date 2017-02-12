/**
 * 
 */
package br.com.lotto.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lotto.dao.ConcursoMegaSenaRepository;
import br.com.lotto.entity.MegaSena;

/**
 * @author Juliano
 *
 */
@Service
public class ConcursoMegaSenaService {

	
	@Autowired
	private ConcursoMegaSenaRepository concursoMegaSenaRepository;
	
	public Collection<MegaSena> buscartodos() {
		return this.concursoMegaSenaRepository.findAll();
	}

}
