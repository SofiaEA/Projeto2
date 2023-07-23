package com.example.projeto2.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "eventos")
public class Eventos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_evento;
    @Column(nullable = false, unique = true)
    private String nome;
    @Column(nullable = false)
    private String local;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private Integer capacidade_max;
    @Column(nullable = false)
    private String data;
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categorias id_categoria;
    @ManyToOne
    @JoinColumn(name = "id")
    private UserModel id_user;

    @OneToMany(mappedBy = "eventos")
    private List<Bilhetes> bilhetes;

    public Integer getId_evento() {
        return id_evento;
    }

    public void setId_evento(Integer id_evento) {
        this.id_evento = id_evento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getCapacidade_max() {
        return capacidade_max;
    }

    public void setCapacidade_max(Integer capacidade_max) {
        this.capacidade_max = capacidade_max;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Categorias getIdCategoria() {
        return id_categoria;
    }

    public void setIdCategoria(Categorias id_categoria) {
        this.id_categoria = id_categoria;
    }

    public UserModel getIdUser() {
        return id_user;
    }

    public void setIdUser(UserModel id_user) {
        this.id_user = id_user;
    }


    public List<Bilhetes> getBilhetes() {
        return bilhetes;
    }

    public void setBilhetes(List<Bilhetes> bilhetes) {
        this.bilhetes = bilhetes;
    }


}