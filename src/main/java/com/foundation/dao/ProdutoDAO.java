package com.foundation.dao;

import org.springframework.data.repository.CrudRepository;

import com.foundation.model.Produto;

public interface ProdutoDAO extends CrudRepository<Produto, Long> {

}
