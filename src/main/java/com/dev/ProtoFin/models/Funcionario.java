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
@Table(name = "funcionario")
public class Funcionario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;

	private Double salarioBruto;

	@Temporal(TemporalType.DATE)
	private Date dataEntrada;

	@Temporal(TemporalType.DATE)
	private Date dataSaida;

	private String cargo;

	@ManyToOne
	private Cidade cidade;

	private String logradouro;

	private String numero;

	private String complemento;

	private String bairro;

	private String uf;

	private String cep;

	private String email;

	private String senha;

	public Funcionario() {
		super();
	}

	public String getBairro() {
		return bairro;
	}

	public String getCargo() {
		return cargo;
	}

	public String getCep() {
		return cep;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public String getComplemento() {
		return complemento;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public String getEmail() {
		return email;
	}

	public Long getId() {
		return id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getNome() {
		return nome;
	}

	public String getNumero() {
		return numero;
	}

	public Double getSalarioBruto() {
		return salarioBruto;
	}

	public String getSenha() {
		return senha;
	}

	public String getUf() {
		return uf;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setSalarioBruto(Double salarioBruto) {
		this.salarioBruto = salarioBruto;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

}
