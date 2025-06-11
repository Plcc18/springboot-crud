package com.example.springboot_crud.repository;

import com.example.springboot_crud.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

//Implementa a interface interface JpaRepository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
