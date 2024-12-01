package com.dev.ProtoFin.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "entrada_produto")
public class EntradaProduto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Funcionario funcionario;

	private Date dataEntrada = new Date();

	private String observacao;

	private String fornecedor;

	public EntradaProduto() {
		super();
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public Long getId() {
		return id;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
