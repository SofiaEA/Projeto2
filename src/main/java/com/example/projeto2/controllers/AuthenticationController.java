package com.example.projeto2.controllers;

import com.example.projeto2.AuthenticationDTO;
import com.example.projeto2.LoginResponseDTO;
import com.example.projeto2.RegisterDTO;
import com.example.projeto2.config.TokenService;
import com.example.projeto2.models.User;
import com.example.projeto2.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public ModelAndView showLoginForm() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @GetMapping("/index")
    public ModelAndView showInicioForm() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @GetMapping("/registar")
    public ModelAndView getNewForm() {
        ModelAndView modelAndView = new ModelAndView("registar");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestBody @Validated AuthenticationDTO data, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("login");
            return modelAndView;
        }

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        if (auth.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(auth);
            modelAndView.setViewName("redirect:/index");
        } else {
            modelAndView.setViewName("login");
        }

        return modelAndView;
    }

    @PostMapping("/registar")
    public RedirectView register(@RequestBody @Validated RegisterDTO data) {
        if (this.userRepository.findByUsername(data.username()) != null) {
            // Usuário já existe, retorne o redirecionamento para a página de registro novamente
            return new RedirectView("/auth/registar");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.username(), encryptedPassword, data.role());
        this.userRepository.save(newUser);

        // Redirecionar para a página de login após o registro bem-sucedido
        return new RedirectView("/auth/login");
    }

//    @PostMapping("/registar")
//    public ResponseEntity register(@RequestBody @Validated RegisterDTO data){
//        if(this.userRepository.findByUsername(data.username()) != null) return ResponseEntity.badRequest().build();
//
//        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
//        User newUser = new User(data.username(), encryptedPassword, data.role());
//
//        this.userRepository.save(newUser);
//
//        return ResponseEntity.ok().build();
//    }

}
