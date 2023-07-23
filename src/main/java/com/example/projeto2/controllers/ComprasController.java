package com.example.projeto2.controllers;

import com.example.projeto2.models.*;
import com.example.projeto2.repository.UserRepository;
import com.example.projeto2.services.*;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
public class ComprasController {
    @Resource(name = "comprasService")
    private compras comprasService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/participanteEventos/listaBilhetesComprados")
    public ModelAndView getAllCompras() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserModel> userDetails = userRepository.findByUsername(username);
        UserModel id_user = userDetails.get();
        ModelAndView modelAndView = new ModelAndView("listaBilhetesComprados");
        List<Compras> listaBilhetesComprados = comprasService.getAllComprasUser(id_user.getId());
        modelAndView.addObject("compras", listaBilhetesComprados);
        return modelAndView;
    }
}