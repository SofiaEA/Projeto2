package com.example.projeto2.controllers;

import com.example.projeto2.models.TipoUser;
import com.example.projeto2.services.tipoUser;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tipouser")
public class TipoUserController {
    @Resource(name = "tipoUserService")
    private tipoUser tipoUserService;

    @PostMapping("/addTipoUser")
    public TipoUser addTipoUser(final @RequestParam String nome) {
        return tipoUserService.addTipoUser(nome);    }
}
