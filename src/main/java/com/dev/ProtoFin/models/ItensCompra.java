package com.dev.ProtoFin.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itens_compra")
public class ItensCompra implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Produto produto;

	@ManyToOne
	private Compra compra;

	private Integer quantidade = 0;

	private Double valorUnitario = 0.;

	private Double valorTotal = 0.;

	public ItensCompra() {
		super();
	}

	public Compra getCompra() {
		return compra;
	}

	public Long getId() {
		return id;
	}

	public Produto getProduto() {
		return produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

}
