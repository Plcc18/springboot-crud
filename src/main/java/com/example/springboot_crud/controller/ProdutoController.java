package com.example.springboot_crud.controller;

import com.example.springboot_crud.model.Produto;
import com.example.springboot_crud.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos", description = "API para gerenciar produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @Operation(summary = "Listar todos os produtos", description = "Retorna todos os produtos da lista cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Produto.class)))
    })
    @GetMapping
    public List<Produto> listarTodos() {
        return service.listarTodos();
    }

    @Operation(summary = "Buscar produto por ID", description = "Retorna um produto com base no seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Produto.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Salvar produto cadastrado", description = "Salva o produto que foi cadastrado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto salvo com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Produto.class)))
    })
    @PostMapping
    public Produto salvar(@RequestBody Produto produto) {
        return service.salvar(produto);
    }

    @Operation(summary = "Atualizar produto cadastrado", description = "Atualiza o produto que foi cadastrado pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Produto.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content)
    })
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
                    return ResponseEntity.ok(service.salvar(p));
                }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deletar produto cadastrado", description = "Deleta o produto que foi cadastrado pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(p -> {
                    service.deletar(id);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
