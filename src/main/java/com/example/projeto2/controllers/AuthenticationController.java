package com.example.projeto2.controllers;

import com.example.projeto2.AuthenticationDTO;
import com.example.projeto2.LoginResponseDTO;
import com.example.projeto2.RegisterDTO;
import com.example.projeto2.config.TokenService;
import com.example.projeto2.models.User;
import com.example.projeto2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

//    @GetMapping("/registar")
//    public ModelAndView getNewForm() {
//        ModelAndView modelAndView = new ModelAndView("registar");
//        modelAndView.addObject("user", new User());
//        return modelAndView;
//    }
    @GetMapping("/")
    public String showLoginForm() {
        return "login";
    }

//    @PostMapping("/login")
//    public ModelAndView processLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
//        User user = userRepository.findByUsername(username);
//
//        if (user != null && user.getPassword().equals(password)) {
//            return new ModelAndView("redirect:/index");
//        } else {
//            ModelAndView modelAndView = new ModelAndView("login");
//            modelAndView.addObject("error", "Credenciais inválidas");
//            return modelAndView;
//        }
//    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/registar")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO data){
        if(this.userRepository.findByUsername(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
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
