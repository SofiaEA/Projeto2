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
    //@GetMapping("/favourites")
    //public EnvelopeDTO<ProductSimpleDTO> getFavourites(final @RequestParam int id, final @RequestParam int offset, final @RequestParam int numItems)
    //@PostMapping("/password/recover/confirm/{customer_id}")
    //public boolean confirmRecoverPassword(final @PathVariable int customer_id);

    @PostMapping("/addTipoUser")
    public TipoUser addTipoUser(final @RequestParam String nome) {
        return tipoUserService.addTipoUser(nome);    }
}
