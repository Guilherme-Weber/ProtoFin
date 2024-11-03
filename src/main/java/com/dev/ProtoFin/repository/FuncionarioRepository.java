package com.dev.ProtoFin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.ProtoFin.models.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
