package com.foundation.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.foundation.filtroConsulta.FiltroConsultaProduto;
import com.foundation.model.Produto;

public interface ProdutoDAO extends CrudRepository<Produto, Long> {

	Page<Produto> findAll(Pageable pageable);

	@SuppressWarnings("all")
	@Query(" SELECT p FROM Produto p "
			+ " WHERE (:#{#filtro.nomeLike} IS NULL "
				+ " OR LOWER(p.nome) LIKE :#{#filtro.nomeLike}) "
			+ " AND (:#{#filtro.idProduto} IS NULL OR p.idProduto = :#{#filtro.idProduto}) "
			+ " AND (:#{#filtro.status} IS NULL OR p.status = :#{#filtro.status}) ")
	Page<Produto> findByFilter(@Param("filtro") FiltroConsultaProduto filtro, Pageable page);
}
