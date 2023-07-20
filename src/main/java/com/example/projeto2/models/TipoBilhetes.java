package com.example.projeto2.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tipobilhetes")
public class TipoBilhetes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tipo_bilhete;

    @Column(nullable = false)
    private String nome;

    public Integer getId_tipo_bilhete() {
        return id_tipo_bilhete;
    }

    public void setId_tipo_bilhete(Integer id_tipo_bilhete) {
        this.id_tipo_bilhete = id_tipo_bilhete;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
