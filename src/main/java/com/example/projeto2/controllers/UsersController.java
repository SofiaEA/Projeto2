package com.example.projeto2.controllers;

import com.example.projeto2.dtos.RegisterDTO;
import com.example.projeto2.models.UserModel;
import com.example.projeto2.models.UserRole;
import com.example.projeto2.repository.UserRepository;
import com.example.projeto2.services.UserNotFoundException;
import com.example.projeto2.services.UserDetailsServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;


@Controller
public class UsersController {
    @Resource(name = "userService")
    private UserDetailsServiceImpl userService;

    private final UserRepository userRepository;

    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public ModelAndView getUsers() {
        ModelAndView modelAndView = new ModelAndView("users");
        List<UserModel> userModels = userService.getAllUsers();
        System.out.println(userModels);
        modelAndView.addObject("users", userModels);
        return modelAndView;
    }

    @GetMapping("users/newUser")
    public ModelAndView getNewForm() {
        ModelAndView modelAndView = new ModelAndView("user_form");
        modelAndView.addObject("user", new UserModel());
        return modelAndView;
   }

    @PostMapping("users/save")
    public ModelAndView saveUser(@ModelAttribute @Validated RegisterDTO data) {
        ModelAndView modelAndView = new ModelAndView("users");

        if (this.userRepository.findByUsername(data.name()).isPresent()) {
            modelAndView.addObject("errorMessage", "Esse email já existe!");
            return modelAndView;
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserModel newUserModel = new UserModel(data.name(), data.username(), encryptedPassword, data.phoneNumber(), UserRole.valueOf(data.userRole().toUpperCase()));

        userService.save(newUserModel);
        modelAndView.addObject("message", "O utilizador foi adicionado com sucesso!");
        modelAndView.setViewName("redirect:/users");
        return modelAndView;
    }

    @GetMapping("users/edit/{id_user}")
    public ModelAndView showEditForm(@PathVariable("id_user") Integer id_user) throws UserNotFoundException {
        UserModel userModel = userService.getUserById(id_user);
        ModelAndView modelAndView = new ModelAndView("user_form");
        modelAndView.addObject("user", userModel);
        return modelAndView;
    }

    @GetMapping("/users/delete/{id_user}")
    public ModelAndView deleteUser(@PathVariable("id_user") Integer id_user, RedirectAttributes ra)  {
        userService.deleteUser(id_user);
        ModelAndView modelAndView = new ModelAndView("redirect:/users");
        ra.addFlashAttribute("messagedelete", "O utilizador de ID=" + id_user + " foi eliminado com sucesso!");
        return modelAndView;
    }
}



