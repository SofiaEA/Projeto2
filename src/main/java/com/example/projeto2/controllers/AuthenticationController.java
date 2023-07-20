package com.example.projeto2.controllers;

import com.example.projeto2.models.User;
import com.example.projeto2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registar")
    public ModelAndView getNewForm() {
        ModelAndView modelAndView = new ModelAndView("registar");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }
    @GetMapping("/")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView processLogin(@RequestParam("email") String email, @RequestParam("password") String password) {
        User user = userRepository.findByUsername(email);

        if (user != null && user.getPassword().equals(password)) {
            return new ModelAndView("redirect:/index");
        } else {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("error", "Credenciais inválidas");
            return modelAndView;
        }
    }

    @GetMapping("/index")
    public String showInicioForm() {
        return "index";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }

}
