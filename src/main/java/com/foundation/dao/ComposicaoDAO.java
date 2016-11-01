package com.foundation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.foundation.model.Composicao;

public interface ComposicaoDAO extends CrudRepository<Composicao, Long> {

	@Query("SELECT DISTINCT c FROM Composicao c WHERE c.insumo.id = ?1")
	public List<Composicao> findAllByInsumo(Long idInsumo);
}
