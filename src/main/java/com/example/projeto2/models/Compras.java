package com.example.projeto2.models;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "compras")
public class Compras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_compra;
    @OneToOne
    @JoinColumn(name = "id_user")
    private User id_user;
    @OneToOne
    @JoinColumn(name = "id_bilhete")
    private Bilhetes id_bilhete;
    @Column(nullable = false)
    private Integer num_bilhetes;
    @Column(nullable = false)
    private Float total_preco;
    @Column(name = "data_compra")
    private java.sql.Timestamp data_compra;

    public Integer getId_compra() {
        return id_compra;
    }

    public void setId_compra(Integer id_compra) {
        this.id_compra = id_compra;
    }

    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }

    public Bilhetes getId_bilhete() {
        return id_bilhete;
    }

    public void setId_bilhete(Bilhetes id_bilhete) {
        this.id_bilhete = id_bilhete;
    }

    public Integer getNum_bilhetes() {
        return num_bilhetes;
    }

    public void setNum_bilhetes(Integer num_bilhetes) {
        this.num_bilhetes = num_bilhetes;
    }

    public Float getTotal_preco() {
        return total_preco;
    }

    public void setTotal_preco(Float total_preco) {
        this.total_preco = total_preco;
    }

    public Timestamp getData_compra() {
        return data_compra;
    }

    public void setData_compra(Timestamp data_compra) {
        this.data_compra = data_compra;
    }
}