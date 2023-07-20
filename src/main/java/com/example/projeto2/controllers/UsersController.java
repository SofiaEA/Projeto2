package com.example.projeto2.controllers;

import com.example.projeto2.models.User;
import com.example.projeto2.services.UserNotFoundException;
import com.example.projeto2.services.users;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;



@RestController
public class UsersController {
    @Resource(name = "userService")
    private users userService;


    @GetMapping("/users")
    public ModelAndView getUsers() {
        ModelAndView modelAndView = new ModelAndView("users");
        List<User> users = userService.getAllUsers();
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/users/newUser")
    public ModelAndView getNewForm() {
        ModelAndView modelAndView = new ModelAndView("user_form");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }


    @PostMapping("/users/save")
    public ModelAndView saveUser(User user, RedirectAttributes ra) {
        userService.save(user);
        ModelAndView modelAndView = new ModelAndView("redirect:/users");
        ra.addFlashAttribute("message", "O utilizador foi adicionado com sucesso!");
        return modelAndView;
    }

    @GetMapping("/users/edit/{id_user}")
    public ModelAndView showEditForm(@PathVariable("id_user") Integer id_user) throws UserNotFoundException {
        User user = userService.getUserById(id_user);
        ModelAndView modelAndView = new ModelAndView("user_form");
        modelAndView.addObject("user", user);
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



