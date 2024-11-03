package com.dev.ProtoFin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.ProtoFin.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
