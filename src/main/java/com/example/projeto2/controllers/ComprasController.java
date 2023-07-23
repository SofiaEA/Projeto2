package com.example.projeto2.controllers;

import com.example.projeto2.models.*;
import com.example.projeto2.services.*;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ComprasController {
    @Resource(name = "comprasService")
    private compras comprasService;

    @GetMapping("/participanteEventos/listaBilhetesComprados")
    public ModelAndView getAllCompras() {
        ModelAndView modelAndView = new ModelAndView("listaBilhetesComprados");
        List<Compras> listaBilhetesComprados = comprasService.getAllCompras();
        modelAndView.addObject("compras", listaBilhetesComprados);
        return modelAndView;
    }
}