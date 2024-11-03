package com.dev.ProtoFin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.ProtoFin.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
