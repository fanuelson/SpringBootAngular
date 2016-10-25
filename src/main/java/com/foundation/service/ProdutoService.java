package com.foundation.service;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.RequestScope;

import com.foundation.dao.ProdutoDAO;
import com.foundation.model.Composicao;
import com.foundation.model.Produto;
import com.foundation.validador.ValidadorComposicao;
import com.foundation.validador.ValidadorProduto;

@Service
@RequestScope
public class ProdutoService extends AbstractService {

	@Autowired
	private ValidadorProduto validacoesProduto;
	
	@Autowired
	private ValidadorComposicao validacoesComposicao;
	
	@Autowired
	private ProdutoDAO produtoDAO;

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Page<Produto> findAll(Pageable page) {
		return produtoDAO.findAll(page);
	}

	public Produto save(Produto produto) {
		limparValidacoes();
		montarComposicoes(produto);
		validar(produto);
		return produtoDAO.save(produto);
	}

	private void validar(Produto produto) {
		validacoesProduto.validarSalvar(produto, this);
		validacoesComposicao.validarSalvar(produto.getComposicoes(), getValidacoes());
		assertValid();
	}

	public void delete(Long id) {
		produtoDAO.delete(id);
	}
	
	public void delete(Produto produto) {
		produtoDAO.delete(produto);
	}
	
	private void montarComposicoes(Produto produto) {
		if(CollectionUtils.isNotEmpty(produto.getComposicoes())) {
			for (Composicao composicao : produto.getComposicoes()) {
				composicao.setProduto(produto);
			}
		}
	}

}