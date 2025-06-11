package com.example.springboot_crud.repository;

import com.example.springboot_crud.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

//Herança da interface JpaRepository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
