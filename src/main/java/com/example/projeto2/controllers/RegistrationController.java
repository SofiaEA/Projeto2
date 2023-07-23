package com.example.projeto2.controllers;

import com.example.projeto2.dtos.RegisterDTO;
import com.example.projeto2.models.UserModel;
import com.example.projeto2.models.UserRole;
import com.example.projeto2.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserRepository userRepository;

    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/signup")
    public ModelAndView getNewForm() {
        return new ModelAndView("signup");
    }

    @PostMapping("/signup")
    public ModelAndView register(@ModelAttribute @Validated RegisterDTO data) {
        ModelAndView modelAndView = new ModelAndView("index");
        if (this.userRepository.findByUsername(data.name()).isPresent()) {
            modelAndView.addObject("errorMessage", "Esse email já existe!");
            return modelAndView;
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserModel newUserModel = new UserModel(data.name(), data.username(), encryptedPassword, data.phoneNumber(), UserRole.valueOf(data.userRole().toUpperCase()));
        this.userRepository.save(newUserModel);
        modelAndView.addObject("successMessage", "Conta criada com sucesso!!");
        return modelAndView;
    }
}
