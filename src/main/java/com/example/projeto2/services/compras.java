package com.example.projeto2.services;

import com.example.projeto2.models.*;
import com.example.projeto2.repository.BilhetesRepository;
import com.example.projeto2.repository.ComprasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("comprasService")
public class compras {
    @Autowired
    private ComprasRepository rep;

    public void save(Compras compra) {
        rep.save(compra);
    }

    public List<Compras> getAllCompras() {
        List<Compras> compras = rep.findAll();
        return compras;
    }

    public List<Compras> getBilhetesDoEvento(Integer id_evento) {
        return rep.findByEventoId(id_evento);
    }

}