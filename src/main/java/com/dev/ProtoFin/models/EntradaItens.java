package com.dev.ProtoFin.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "entrada_itens")
public class EntradaItens implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private EntradaProduto entrada;

	@ManyToOne
	private Produto produto;

	private Double quantidade = 0.;

	private Double valorProduto = 0.;

	private Double valorVenda = 0.;

	public EntradaItens() {
		super();
	}

	public EntradaProduto getEntrada() {
		return entrada;
	}

	public Long getId() {
		return id;
	}

	public Produto getProduto() {
		return produto;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public Double getValorProduto() {
		return valorProduto;
	}

	public Double getValorVenda() {
		return valorVenda;
	}

	public void setEntrada(EntradaProduto entrada) {
		this.entrada = entrada;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public void setValorProduto(Double valorProduto) {
		this.valorProduto = valorProduto;
	}

	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}
}
