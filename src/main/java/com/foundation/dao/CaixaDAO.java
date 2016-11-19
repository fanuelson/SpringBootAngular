package com.foundation.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.foundation.model.Caixa;

public interface CaixaDAO extends CrudRepository<Caixa, Long> {

	@Query("SELECT c FROM Caixa c WHERE c.dataFechamento IS NULL")
	public Caixa findCaixaAberto();
}
