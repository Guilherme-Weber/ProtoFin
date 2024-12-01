package com.dev.ProtoFin.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cidade")
public class Cidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;

	@ManyToOne
	private Estado estado;

	public Cidade() {
		super();
	}

	public Estado getEstado() {
		return estado;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return nome + " - " + estado.getSigla();
	}

}
