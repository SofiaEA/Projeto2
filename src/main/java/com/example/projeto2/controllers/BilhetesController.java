package com.example.projeto2.controllers;

import com.example.projeto2.dtos.CompraDTO;
import com.example.projeto2.models.*;
import com.example.projeto2.repository.UserRepository;
import com.example.projeto2.services.*;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@RestController
public class BilhetesController {
    @Resource(name = "bilhetesService")
    private bilhetes bilhetesService;

    @Autowired
    private UserRepository userRepository;

    private final eventos eventoService;

    private final compras comprasService;

    private final UserDetailsServiceImpl userService;

    public BilhetesController(eventos eventoService, compras comprasService, UserDetailsServiceImpl userService) {
        this.eventoService = eventoService;
        this.comprasService = comprasService;
        this.userService = userService;
    }

    @GetMapping("/eventosOrganizador/bilhetes")
    public ModelAndView getBilhetes() {
        ModelAndView modelAndView = new ModelAndView("bilhetes");
        List<Bilhetes> bilhetes = bilhetesService.getAllBilhetes();
        modelAndView.addObject("bilhetes", bilhetes);
        return modelAndView;
    }

    @GetMapping("/eventosOrganizador/bilhetesEvento/{id}")
    public ModelAndView getBilhetesEvento(@PathVariable("id") Integer id_evento) {
        ModelAndView modelAndView = new ModelAndView("listaBilhetesEvento");
        List<Bilhetes> bilhetes = bilhetesService.getBilhetesDoEvento(id_evento);
        modelAndView.addObject("bilhetesDoEvento", bilhetes);
        return modelAndView;
    }


    @GetMapping("/participanteEventos/listaEventos/listaBilhetes")
    public ModelAndView listaBilhetes(@RequestParam("id_evento") int idEvento) {
        ModelAndView modelAndView = new ModelAndView();

        List<Bilhetes> listaBilhetes = bilhetesService.getBilhetesDoEvento(idEvento);

        modelAndView.setViewName("listaBilhetes");
        modelAndView.addObject("listaBilhetes", listaBilhetes);

        return modelAndView;
    }

    @GetMapping("/eventosOrganizador/bilhetes/newBilhete")
    public ModelAndView getNewForm() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserModel> userDetails = userRepository.findByUsername(username);
        UserModel id_user = userDetails.get();
        ModelAndView modelAndView = new ModelAndView("bilhete_form");
        modelAndView.addObject("bilhete", new Bilhetes());
        List<Eventos> eventos = eventoService.getAllEventosOrganizador(id_user.getId());
        modelAndView.addObject("eventos", eventos);
        return modelAndView;
    }

    @PostMapping("/eventosOrganizador/bilhetes/save")
    public ModelAndView saveBilhete(@ModelAttribute("bilhete") Bilhetes bilhete, RedirectAttributes ra) {
        bilhete.setBilhetes_disp(bilhete.getNum_bilhetes());
        bilhete.setBilhetes_comprados(0);

        bilhetesService.save(bilhete);

        ModelAndView modelAndView = new ModelAndView("redirect:/eventosOrganizador/bilhetes");
        ra.addFlashAttribute("message", "O bilhete foi adicionado com sucesso!");
        return modelAndView;
    }

    @GetMapping("/participanteEventos/listaEventos/listaBilhetes/comprarBilhetes/{id}")
    public ModelAndView comprarBilhetes(@PathVariable("id") Integer id_bilhete) throws BilheteNotFoundException {
        Bilhetes bilhete = bilhetesService.getBilheteById(id_bilhete);
        ModelAndView modelAndView = new ModelAndView("comprarBilhetes");
        modelAndView.addObject("comprarBilhetes", bilhete);
        return modelAndView;
    }

    @PostMapping("/participanteEventos/comprarBilhetes")
    public ModelAndView confirmarCompra(@ModelAttribute @Validated CompraDTO compraDTO, BindingResult result, RedirectAttributes ra) throws BilheteNotFoundException, UserNotFoundException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserModel> userDetails = userRepository.findByUsername(username);
        UserModel user = userDetails.get();
        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("/participanteEventos/comprarBilhetes");
            modelAndView.addObject("mensagemErro", "Dados inválidos!");
            return modelAndView;
        }

        Integer idBilhete = compraDTO.id_bilhete();
        Integer quantidade = compraDTO.quantidade();

        try {
            Bilhetes bilhete = bilhetesService.getBilheteById(idBilhete);

            if (quantidade <= 0) {
                ModelAndView modelAndView = new ModelAndView("comprarBilhetes");
                modelAndView.addObject("mensagemErro", "A quantidade deve ser maior que 0.");
                return modelAndView;
            }

            if (bilhete.getBilhetes_disp() < quantidade) {
                ModelAndView modelAndView = new ModelAndView("comprarBilhetes");
                modelAndView.addObject("mensagemErro", "Não há bilhetes suficientes disponíveis!");
                return modelAndView;
            }

            bilhete.setBilhetes_disp(bilhete.getBilhetes_disp() - quantidade);
            bilhete.setBilhetes_comprados(bilhete.getBilhetes_comprados() + quantidade);

            bilhetesService.save(bilhete);

            Compras compra = new Compras();
            compra.setId_user(user);
            compra.setId_bilhete(bilhete);
            compra.setNum_bilhetes(quantidade);
            compra.setTotal_preco(bilhete.getPrecototal() * quantidade);
            compra.setData_compra(new Timestamp(System.currentTimeMillis()));

            comprasService.save(compra);


            ra.addFlashAttribute("mensagemCompra", "Compra efetuada com sucesso! Bilhetes Comprados: " + quantidade);
            ModelAndView modelAndView = new ModelAndView("redirect:/participanteEventos/listaEventos");
            return modelAndView;

        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("comprarBilhetes");
            modelAndView.addObject("mensagemErro", "Ocorreu um erro ao processar a compra.");
            return modelAndView;
        }
    }
}