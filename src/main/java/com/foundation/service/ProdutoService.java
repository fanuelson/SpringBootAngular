package com.foundation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import com.foundation.dao.ProdutoDAO;
import com.foundation.model.Composicao;
import com.foundation.model.Produto;
import com.foundation.validador.ValidacoesComposicao;
import com.foundation.validador.ValidacoesProduto;

@Service
@RequestScope
public class ProdutoService {

	@Autowired
	private ValidacoesProduto validacoesProduto;
	
	@Autowired
	private ValidacoesComposicao validacoesComposicao;
	
	@Autowired
	private ProdutoDAO produtoDAO;

	public Page<Produto> findAll(Pageable page) {
		return produtoDAO.findAll(page);
	}

	public Produto save(Produto produto) {
		montarParametros(produto);
		validacoesProduto.validarSalvar(produto);
		validacoesComposicao.validarSalvar(produto.getComposicoes());
		return produtoDAO.save(produto);
	}

	public void delete(Long id) {
		produtoDAO.delete(id);
	}
	
	public void delete(Produto produto) {
		produtoDAO.delete(produto);
	}
	
	private void montarParametros(Produto produto) {
		if(produto.getComposicoes() != null) {
			for (Composicao composicao : produto.getComposicoes()) {
				composicao.setProduto(produto);
			}
		}
	}

}