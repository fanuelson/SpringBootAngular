package com.foundation.model;

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

import org.springframework.hateoas.ResourceSupport;

import com.foundation.enums.AtivoInativoEnum;

@Entity
public class Produto extends ResourceSupport {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long idProduto;

	@Column(name = "nome")
	private String nome;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private AtivoInativoEnum status;
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Composicao> composicoes = new ArrayList<>();

	public void gastarInsumos(){
		for (Composicao composicao : composicoes) {
			composicao.gastarInsumo();
		}
	}
	
	public boolean possuiInsumosSuficientes() {
		for (Composicao composicao : composicoes) {
			if(!composicao.possuiInsumoSuficiente()) {
				return false;
			}
		}
		return true;
	}
	
	public void toggleStatus() {
		if(this.status.equals(AtivoInativoEnum.A)){
			this.status = AtivoInativoEnum.I;
		} else if(this.status.equals(AtivoInativoEnum.I)){
			this.status = AtivoInativoEnum.A;
		}
	}
	
	public boolean possuiInsumo(Insumo insumo){
		for (Composicao composicao : this.composicoes) {
			if(composicao.getInsumo().equals(insumo)) {
				return true;
			}
		}
		return false;
	}
	
	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Composicao> getComposicoes() {
		return composicoes;
	}

	public void setComposicoes(List<Composicao> composicoes) {
		this.composicoes = composicoes;
	}
	
	public AtivoInativoEnum getStatus() {
		return status;
	}

	public void setStatus(AtivoInativoEnum status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProduto == null) ? 0 : idProduto.hashCode());
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
		Produto other = (Produto) obj;
		if (idProduto == null) {
			if (other.idProduto != null)
				return false;
		} else if (!idProduto.equals(other.idProduto))
			return false;
		return true;
	}

}
