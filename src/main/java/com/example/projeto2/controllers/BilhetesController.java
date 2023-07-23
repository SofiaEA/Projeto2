package com.example.projeto2.controllers;

import com.example.projeto2.models.*;
import com.example.projeto2.services.*;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@RestController
public class BilhetesController {
    @Resource(name = "bilhetesService")
    private bilhetes bilhetesService;

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
        ModelAndView modelAndView = new ModelAndView("bilhete_form");
        modelAndView.addObject("bilhete", new Bilhetes());
        List<Eventos> eventos = eventoService.getAllEventos();
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

//    @GetMapping
//    @PostMapping("/participanteEventos/comprarBilhetes")
//    public ModelAndView confirmarCompra(@RequestParam("id_bilhete") int id_bilhete,
//                                        @RequestParam("quantidade") int quantidade, RedirectAttributes ra) throws BilheteNotFoundException, UserNotFoundException {
//        ModelAndView modelAndView = new ModelAndView();
//        Bilhetes bilhete = bilhetesService.getBilheteById(id_bilhete);
//
//        if (quantidade <= 0) {
//            modelAndView.setViewName("redirect:/comprarBilhetes");
//            ra.addFlashAttribute("mensagemErro", "A quantidade deve ser maior que 0.");
//            return modelAndView;
//        }
//
//        if (bilhete.getBilhetes_disp() < quantidade) {
//            modelAndView.setViewName("redirect:/comprarBilhetes");
//            ra.addFlashAttribute("mensagemErro", "Não há bilhetes suficientes disponíveis!" );
//            return modelAndView;
//        }
//
//        bilhete.setBilhetes_disp(bilhete.getBilhetes_disp() - quantidade);
//        bilhete.setBilhetes_comprados(bilhete.getBilhetes_comprados() + quantidade);
//
//        User user = userService.getUserById(8);
//
//        try {
//            bilhetesService.save(bilhete);
//
//            Compras compra = new Compras();
//            compra.setId_user(user); // Defina o usuário logado na compra
//            compra.setId_bilhete(bilhete);
//            compra.setNum_bilhetes(quantidade);
//            compra.setTotal_preco(bilhete.getPrecototal() * quantidade);
//            compra.setData_compra(new Timestamp(System.currentTimeMillis()));
//
//            comprasService.save(compra);
//
//            ra.addFlashAttribute("mensagemCompra", "Compra efetuada com sucesso! Bilhetes Comprados: " + quantidade);
//            modelAndView.setViewName("redirect:/listaEventos");
//            return modelAndView;
//        } catch (Exception e) {
//            modelAndView.setViewName("redirect:/comprarBilhetes");
//            ra.addFlashAttribute("mensagemErro", "Ocorreu um erro ao processar a compra.");
//            return modelAndView;
//        }
//    }
//
}