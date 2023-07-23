package com.example.projeto2.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

public class LogoutController {


    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Encerrar a sessão atual
        request.getSession().invalidate();

        // Redirecionar para a página inicial ou outra página de sua escolha
        response.sendRedirect("/index");
    }
}
