package com.example.springboot_crud.controller;

import com.example.springboot_crud.model.Produto;
import com.example.springboot_crud.service.ProdutoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/produtos-view")
public class ProdutoViewController {

    private final ProdutoService service;

    public ProdutoViewController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("produtos", service.listarTodos());
        return "listar";
    }

    @GetMapping("/novo")
    public String formNovo(Model model) {
        model.addAttribute("produto", new Produto());
        return "form";
    }

    @PostMapping
    public String salvar(Produto produto) {
        service.salvar(produto);
        return "redirect:/produtos-view";
    }

    @GetMapping("/editar/{id}")
    public String formEditar(@PathVariable Long id, Model model) {
        Produto produto = service.buscarPorId(id).orElseThrow(() -> new IllegalArgumentException("ID Inválido: " + id));
        model.addAttribute("produto", produto);
        return "form";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable Long id, Produto produto) {
        Produto existente = service.buscarPorId(id).orElseThrow(() -> new IllegalArgumentException("ID Inválido: " + id));
        existente.setNome(produto.getNome());
        existente.setPreco(produto.getPreco());
        service.salvar(existente);
        return "redirect:/produtos-view";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        service.deletar(id);
        return "redirect:/produtos-view";
    }
}
