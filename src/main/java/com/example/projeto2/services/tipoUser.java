package com.example.projeto2.services;

import com.example.projeto2.models.TipoUser;
import com.example.projeto2.models.User;
import com.example.projeto2.repository.TipoUserRepository;
import com.example.projeto2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tipoUserService")
public class tipoUser {
    @Autowired
    private TipoUserRepository rep;

    public TipoUser addTipoUser(String nome) {
        TipoUser tipouser = new TipoUser();
        tipouser.setNome(nome);


        TipoUser savedTipoUser = rep.save(tipouser);

        return savedTipoUser;

    }

}
