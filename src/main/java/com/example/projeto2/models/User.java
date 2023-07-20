package com.example.projeto2.models;

import jakarta.persistence.*;
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_user;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Integer telemovel;
    @OneToOne
    @JoinColumn(name = "id_tipo_user")
    private TipoUser tipo_user;

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(Integer telemovel) {
        this.telemovel = telemovel;
    }

    public TipoUser getTipo_user() {
        return tipo_user;
    }

    public void setTipo_user(TipoUser tipo_user) {
        this.tipo_user = tipo_user;
    }
}