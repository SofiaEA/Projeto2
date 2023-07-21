package com.example.projeto2.services;

import com.example.projeto2.models.Categorias;
import com.example.projeto2.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoriasService")
public class categorias {
    @Autowired
    private CategoriaRepository rep;

    public Categorias addCategorias(String nome) {
        Categorias categorias = new Categorias();
        categorias.setNome(nome);


        Categorias savedCategorias = rep.save(categorias);

        return savedCategorias;
    }

    public List<Categorias> getAllCategorias() {
        List<Categorias> categorias = rep.findAll();
        return categorias;
    }

}
