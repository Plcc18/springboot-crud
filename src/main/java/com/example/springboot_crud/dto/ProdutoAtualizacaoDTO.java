package com.example.springboot_crud.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProdutoAtualizacaoDTO {

    @Schema(description = "Nome do produto", example = "Camiseta Polo")
    private String nome;

    @Schema(description = "Preço do produto", example = "79.90")
    private Double preco;

    //Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
