package com.example.projeto2.controllers;

import com.example.projeto2.models.*;
import com.example.projeto2.repository.UserRepository;
import com.example.projeto2.services.*;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.Optional;

@RestController
public class EventosController {
    @Resource(name = "eventosService")
    private eventos eventosService;

    @Autowired
    private UserRepository userRepository;

    private final bilhetes bilhetesService;

    private final compras comprasService;

    private final categorias categoriasService;

    public EventosController(bilhetes bilhetesService, compras comprasService, categorias categoriasService) {
        this.bilhetesService = bilhetesService;
        this.comprasService = comprasService;
        this.categoriasService = categoriasService;
    }


    @GetMapping("/eventos")
    public ModelAndView getEventos() {
        ModelAndView modelAndView = new ModelAndView("eventos");
        List<Eventos> eventos = eventosService.getAllEventos();
        modelAndView.addObject("eventos", eventos);
        return modelAndView;
    }

    @GetMapping("/participanteEventos/listaEventos")
    public ModelAndView getListaEventos(String keyword) {
        ModelAndView modelAndView = new ModelAndView("listaEventos");
        if (keyword != null) {
            List<Eventos> listaEventos = eventosService.getByKeyword(keyword);
            modelAndView.addObject("listaEventos", listaEventos);
        } else {
            List<Eventos> listaEventos = eventosService.getListaEventos();
            modelAndView.addObject("listaEventos", listaEventos);
        }
        return modelAndView;
    }

    @GetMapping("/eventosOrganizador")
    public ModelAndView getEventosOrganizador() {
        ModelAndView modelAndView = new ModelAndView("eventosOrganizador");
        List<Eventos> eventosOrganizador = eventosService.getAllEventosOrganizador();
        modelAndView.addObject("eventosOrganizador", eventosOrganizador);
        return modelAndView;
    }

    @PostMapping("/eventosOrganizador/save")
    public ModelAndView saveUser(Eventos evento, RedirectAttributes ra) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserModel> userDetails = userRepository.findByUsername(username);

        if (userDetails.isPresent()) {
            UserModel user = userDetails.get();
            evento.setIdUser(user);

            eventosService.save(evento);

            ModelAndView modelAndView = new ModelAndView("redirect:/eventosOrganizador");
            ra.addFlashAttribute("message", "O evento foi adicionado com sucesso!");
            return modelAndView;
        } else {
            // Tratar o cenário em que o usuário logado não é encontrado
            // Redirecionar para uma página de erro ou fazer qualquer tratamento apropriado
            return new ModelAndView("redirect:/pagina-de-erro");
        }
    }

    @GetMapping("/eventosOrganizador/newEvento")
    public ModelAndView getNewForm() {
        ModelAndView modelAndView = new ModelAndView("evento_form");
        modelAndView.addObject("evento", new Eventos());
        return modelAndView;
    }

    @GetMapping("/eventosOrganizador/edit/{id_evento}")
    public ModelAndView showEditForm(@PathVariable("id_evento") Integer id_evento) throws EventoNotFoundException {
        Eventos evento = eventosService.getUserById(id_evento);
        ModelAndView modelAndView = new ModelAndView("evento_form");
        modelAndView.addObject("evento", evento);
        return modelAndView;
    }

    @GetMapping("/eventosOrganizador/relatorio/{id_evento}")
    public ModelAndView eventoDetalhes(@PathVariable("id_evento") Integer id_evento) throws EventoNotFoundException {
        Eventos evento = eventosService.getEventoById(id_evento);
        List<Bilhetes> bilhetes = bilhetesService.getBilhetesDoEvento(id_evento);
        List<Compras> compras = comprasService.getBilhetesDoEvento(id_evento);
        ModelAndView modelAndView = new ModelAndView("detalhesEvento");
        modelAndView.addObject("evento", evento);
        modelAndView.addObject("bilhetes", bilhetes);
        modelAndView.addObject("compras", compras);
        return modelAndView;
    }


}
