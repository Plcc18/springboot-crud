package com.example.springboot_crud.controller;

import com.example.springboot_crud.model.Produto;
import com.example.springboot_crud.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    //Instância Service
    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @Operation(summary = "Listar todos os produtos", description = "Retorna todos os produtos da lista cadastrados")
    @GetMapping
    public List<Produto> listarTodos() {
        return service.listarTodos();
    }
}
