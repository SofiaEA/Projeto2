package com.example.projeto2.services;

import com.example.projeto2.models.TipoBilhetes;
import com.example.projeto2.repository.TipoBilheteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tipoBilhetesService")
public class tipoBilhetes {
    @Autowired
    private TipoBilheteRepository rep;

    public TipoBilhetes addTipoBilhetes(String nome) {
        TipoBilhetes tipoBilhetes = new TipoBilhetes();
        tipoBilhetes.setNome(nome);


        TipoBilhetes savedTipoBilhetes = rep.save(tipoBilhetes);

        return savedTipoBilhetes;

    }
}