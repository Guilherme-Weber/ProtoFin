package com.dev.ProtoFin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev.ProtoFin.models.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	@Query("from Funcionario where email=?1")
	public List<Funcionario> buscarFuncionariEmail(String email);

}
