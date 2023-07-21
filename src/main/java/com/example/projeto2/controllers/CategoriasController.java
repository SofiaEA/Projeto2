package com.example.projeto2.controllers;

import com.example.projeto2.models.Categorias;
import com.example.projeto2.services.categorias;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {
    @Resource(name = "categoriasService")
    private categorias categoriaService;

    @PostMapping("/addCategoria")
    public Categorias addCategorias(final @RequestParam String nome) {
        return categoriaService.addCategorias(nome);    }
}
