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
    //@GetMapping("/favourites")
    //public EnvelopeDTO<ProductSimpleDTO> getFavourites(final @RequestParam int id, final @RequestParam int offset, final @RequestParam int numItems)
    //@PostMapping("/password/recover/confirm/{customer_id}")
    //public boolean confirmRecoverPassword(final @PathVariable int customer_id);

    @PostMapping("/addTipoBilhete")
    public TipoBilhetes addTipoBilhete(final @RequestParam String nome) {
        return tipoBilhetesService.addTipoBilhetes(nome);    }
}