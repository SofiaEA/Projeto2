package com.example.projeto2.controllers;

import com.example.projeto2.dtos.AuthenticationDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    public AuthenticationController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute @Validated AuthenticationDTO data, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("errorMessage", "Bad Request");
            modelAndView.setStatus(HttpStatusCode.valueOf(400));
            modelAndView.setViewName("redirect:/index");
            return modelAndView;
        }

        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            SecurityContextHolder.getContext().setAuthentication(auth);
            modelAndView.addObject("principal", SecurityContextHolder.getContext().getAuthentication());
            modelAndView.addObject("authentication", SecurityContextHolder.getContext().getAuthentication());
            modelAndView.setViewName("redirect:/app/home");

        } catch (AuthenticationException e) {
            modelAndView.addObject("errorMessage", "Login Inválido");
            modelAndView.setViewName("index");
        }

        return modelAndView;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/index";
    }
}
