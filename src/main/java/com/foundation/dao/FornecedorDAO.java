package com.foundation.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.foundation.filtroConsulta.FiltroConsultaFornecedor;
import com.foundation.model.Fornecedor;

public interface FornecedorDAO extends CrudRepository<Fornecedor, Long> {

	Fornecedor findByCpfCnpj(Long cpfCnpj);

	@Query(" SELECT f " + " FROM Fornecedor f "
			+ " WHERE (:#{#filtro.nomeLike} IS NULL OR LOWER(f.nome) LIKE :#{#filtro.nomeLike}) "
			+ " AND (:#{#filtro.cpfCnpjLike} IS NULL OR STR(f.cpfCnpj) LIKE :#{#filtro.cpfCnpjLike}) ")
	@SuppressWarnings("all")
	Page<Fornecedor> findByFilter(@Param("filtro") FiltroConsultaFornecedor filtro, Pageable page);
}
