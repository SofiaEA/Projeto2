package com.example.projeto2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class indexController {
    @GetMapping("/index")
    public ModelAndView showLoginForm() {
        return new ModelAndView("index");
    }


}
