package com.foundation.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.foundation.filtroConsulta.FiltroConsultaInsumo;
import com.foundation.model.Insumo;
import com.foundation.reportVO.SubReportInsumoVO;

public interface InsumoDAO extends CrudRepository<Insumo, Long> {

	Page<Insumo> findAll(Pageable pageable);
	
	@SuppressWarnings("all")
	@Query(" SELECT i FROM Insumo i "
			+ " WHERE (:#{#filtro.nomeOrDescricaoLike} IS NULL "
				+ " OR LOWER(i.nome) LIKE :#{#filtro.nomeOrDescricaoLike} "
				+ " OR LOWER(i.descricao) LIKE :#{#filtro.nomeOrDescricaoLike}) "
			+ " AND (:#{#filtro.quantidadeMin} IS NULL OR i.quantidade >= :#{#filtro.quantidadeMin}) "
			+ " AND (:#{#filtro.quantidadeMax} IS NULL OR i.quantidade <= :#{#filtro.quantidadeMax}) "
			+ " AND (:#{#filtro.medida} IS NULL OR i.medida = :#{#filtro.medida}) ")
	Page<Insumo> findByFilter(@Param("filtro") FiltroConsultaInsumo filtro, Pageable page);
	
	@Query("SELECT new com.foundation.reportVO.SubReportInsumoVO(i)"
			+ "FROM Insumo i")
	List<SubReportInsumoVO> findAllSubReportVo();
	
}
