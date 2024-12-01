package com.dev.ProtoFin.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "produtos")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String descricao;

	private Double valorVenda;

	private String categoria;

	private String marca;

	private Double quantidadeEstoque = 0.;

	private String nomeImagem;

	public Produto() {
		super();
	}

	public String getCategoria() {
		return categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public Long getId() {
		return id;
	}

	public String getMarca() {
		return marca;
	}

	public String getNomeImagem() {
		return nomeImagem;
	}

	public Double getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public Double getValorVenda() {
		return valorVenda;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setNomeImagem(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}

	public void setQuantidadeEstoque(Double quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}

}
