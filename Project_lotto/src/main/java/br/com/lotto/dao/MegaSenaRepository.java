package br.com.lotto.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.lotto.entity.Megasena;

@Repository
public interface MegaSenaRepository extends JpaRepository<Megasena,Integer>{

	@Query(value = "select numeroidnumero , count(*)  from megasenanumero group by numeroidnumero order by numeroidnumero asc",nativeQuery=true)
	public Object[] getFrequencia();
	
	@Query(value = "select * from megasenanumero limit=:plimit(select numeroidnumero , count(*)  from megasenanumero group by numeroidnumero order by numeroidnumero asc)",nativeQuery=true)
	public Object[] getFrequencia(@Param(value = "plimit") Integer plimit);
}
