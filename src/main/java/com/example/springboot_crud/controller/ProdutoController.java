package com.example.springboot_crud.controller;

import com.example.springboot_crud.model.Produto;
import com.example.springboot_crud.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "Buscar produto por ID", description = "Retorna um produto com base no seu ID")
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok) //Retorna 200 OK
                .orElse(ResponseEntity.notFound().build()); //Retorna 404 Not Found
    }

    @Operation(summary = "Salvar produto cadastrado", description = "Salva o produto que foi cadastrado")
    @PostMapping
    public Produto salvar(@RequestBody Produto produto) {
        return service.salvar(produto);
    }

    @Operation(summary = "Atualizar produto cadastrado", description = "Atualiza o produto que foi cadastrado pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        return service.buscarPorId(id)
                .map(p -> {
                    if (produto.getNome() != null) {
                        p.setNome(produto.getNome());
                    }
                    if (produto.getPreco() != null) {
                        p.setPreco(produto.getPreco());
                    }
                    return ResponseEntity.ok(service.salvar(p)); //Retorna 200 OK e salva
                }).orElse(ResponseEntity.notFound().build()); //Retorna 404 Not Found
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(p -> {
                    service.deletar(id);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
