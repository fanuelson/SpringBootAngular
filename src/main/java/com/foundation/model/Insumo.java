package com.foundation.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.foundation.enums.MedidaEnum;

@Entity
public class Insumo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "quantidade")
	private BigDecimal quantidade;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "medida")
	private MedidaEnum medida;
	
	@JsonIgnore
	@OneToMany(mappedBy = "insumo", cascade = {CascadeType.ALL}, orphanRemoval = true)
	private List<Composicao> composicoes = new ArrayList<>();
	
	public boolean possuiQuantidade(BigDecimal quantidade) {
		return this.quantidade.compareTo(quantidade) >= 0;
	}
	
	public void diminuirQuantidade(BigDecimal qtd){
		this.quantidade = this.quantidade.subtract(qtd);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public MedidaEnum getMedida() {
		return medida;
	}

	public void setMedida(MedidaEnum medida) {
		this.medida = medida;
	}
	
	public List<Composicao> getComposicoes() {
		return composicoes;
	}

	public void setComposicoes(List<Composicao> composicoes) {
		this.composicoes = composicoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Insumo other = (Insumo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
