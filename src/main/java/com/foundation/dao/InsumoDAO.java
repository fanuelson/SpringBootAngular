package com.foundation.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.foundation.model.Insumo;

public interface InsumoDAO extends CrudRepository<Insumo, Long> {

	Page<Insumo> findAll(Pageable pageable);
}
