package com.example.projeto2.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tipouser")
public class TipoUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tipo_user;

    @Column(nullable = false)
    private String nome;

    public Integer getId_tipo_user() {
        return id_tipo_user;
    }

    public void setId_tipo_user(Integer id_tipo_user) {
        this.id_tipo_user = id_tipo_user;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
