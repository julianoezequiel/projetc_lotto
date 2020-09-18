package br.com.ot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ot.entity.Numero;

@Repository
public interface NumeroRepository extends JpaRepository<Numero, Integer> {

}
