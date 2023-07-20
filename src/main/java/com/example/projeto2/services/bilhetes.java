package com.example.projeto2.services;

import com.example.projeto2.models.Bilhetes;
import com.example.projeto2.repository.BilhetesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("bilhetesService")
public class bilhetes {
    @Autowired
    private BilhetesRepository rep;

    public void save(Bilhetes bilhete) {
        rep.save(bilhete);
    }
    public List<Bilhetes> getAllBilhetes() {
        List<Bilhetes> bilhetes = rep.findAll();
        return bilhetes;
    }

    public List<Bilhetes> getBilhetesDoEvento(Integer id_evento) {
        return rep.findByEventoId(id_evento);
    }


    public Bilhetes getBilheteById(Integer id_bilhete) throws BilheteNotFoundException {
        Optional<Bilhetes> result = rep.findById(id_bilhete);
        if(result.isPresent()){
            return result.get();
        }
        throw new BilheteNotFoundException("Não se encontra nenhum evento com esse id " + id_bilhete);
    }


}