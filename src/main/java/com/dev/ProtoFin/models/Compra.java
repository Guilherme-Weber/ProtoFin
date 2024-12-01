package com.dev.ProtoFin.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "compra")
public class Compra implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Cliente cliente;

	@ManyToOne
	private Funcionario funcionario;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCompra = new Date();

	private String formaPagamento;

	private Double valorTotal = 0.;

	private String gone;

	public Compra() {
		super();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public String getGone() {
		return gone;
	}

	public Long getId() {
		return id;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public void setGone(String gone) {
		this.gone = gone;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

}
