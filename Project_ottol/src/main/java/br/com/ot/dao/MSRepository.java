package br.com.ot.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ot.entity.MS;

@Repository
public interface MSRepository extends JpaRepository<MS, Integer> {

	@Query(value = "select numeroidnumero , count(*)  from megasenanumero group by numeroidnumero order by numeroidnumero asc", nativeQuery = true)
	public Object[] getFrequencia();

	@Query(value = "SELECT numeroidnumero, count(*) FROM Megasenanumero  WHERE Megasenanumero.megasenaidconcurso <= :megasenaidconcurso group by numeroidnumero order by numeroidnumero asc", nativeQuery = true)
	public Collection<Object[]> getFrequencia(@Param(value = "megasenaidconcurso") Integer ultimo);

	@Query(value = "select m from MS m where m.idconcurso <= :idmega")
	public Collection<MS> buscarMenorQue(@Param("idmega") Integer id);
	
	@Query(value = "select m from MS m where m.idconcurso >= :idmega")
	public Collection<MS> buscarMaiorQue(@Param("idmega") Integer id);

	@Query(value = "select m from MS m where m.idconcurso = :idmega")
	public MS getMegaSena(@Param("idmega") Integer id);

	@Query(value = "select m from MS m ")
	public List<MS> getMegaSenaPage(Sort sort);

	default MS getUltimoConcurso() {
		List<MS> list = getMegaSenaPage(orderBy());
		return list.isEmpty() ? null : list.iterator().next();
	}

	default Sort orderBy() {
		return Sort.by(Sort.Direction.DESC, "concurso");
	}

}
