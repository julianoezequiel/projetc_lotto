package br.com.lotto.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lotto.entity.Numero;

@Repository
public interface NumeroRepository extends JpaRepository<Numero, Integer> {

}
