package br.com.ottol.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ottol.entity.Numero;

@Repository
public interface NumeroRepository extends JpaRepository<Numero, Integer> {

}
