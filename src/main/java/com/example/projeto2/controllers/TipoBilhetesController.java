package com.example.projeto2.controllers;

import com.example.projeto2.models.TipoBilhetes;
import com.example.projeto2.services.tipoBilhetes;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tipobilhetes")
public class TipoBilhetesController {
    @Resource(name = "tipoBilhetesService")
    private tipoBilhetes tipoBilhetesService;

    @PostMapping("/addTipoBilhete")
    public TipoBilhetes addTipoBilhete(final @RequestParam String nome) {
        return tipoBilhetesService.addTipoBilhetes(nome);    }
}