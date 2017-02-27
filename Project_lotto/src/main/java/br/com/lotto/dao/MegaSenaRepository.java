package br.com.lotto.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.lotto.entity.Megasena;
import br.com.lotto.entity.Megasenanumero;

@Repository
public interface MegaSenaRepository extends JpaRepository<Megasena, Integer> {

	@Query(value = "select numeroidnumero , count(*)  from megasenanumero group by numeroidnumero order by numeroidnumero asc", nativeQuery = true)
	public Object[] getFrequencia();

	@Query(value = "SELECT numeroidnumero, count(*) FROM Megasenanumero  WHERE Megasenanumero.megasenaidconcurso <= :megasenaidconcurso group by numeroidnumero order by numeroidnumero asc", nativeQuery = true)
	public List<Object[]> getFrequencia(@Param(value = "megasenaidconcurso") Integer ultimo);

	@Query(value = "select m from Megasena m where m.idconcurso <= :idmega")
	public List<Megasena> buscarMenorQue(@Param("idmega") Integer id);
}
