package com.example.projeto2.controllers;

import com.example.projeto2.repository.UserRepository;
import com.example.projeto2.services.UserDetailsServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/app")
public class AppController {

    @Resource(name = "userService")
    private UserDetailsServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @GetMapping("/perfil")
    public ModelAndView perfil() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserDetails> user = userRepository.findByUsername(username);

        ModelAndView modelAndView = new ModelAndView("perfil");
        modelAndView.addObject("user", user);

        return modelAndView;
    }

}
