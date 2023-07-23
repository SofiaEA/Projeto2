package com.example.projeto2.models;

import jakarta.persistence.*;
@Entity
@Table(name = "bilhetes")
public class Bilhetes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_bilhete;
    @Column(nullable = false)
    private Float precototal;
    @Column(nullable = false)
    private Integer num_bilhetes;
    @Column
    private Integer bilhetes_disp;
    @Column
    private Integer bilhetes_comprados;
    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Eventos eventos;
    @ManyToOne
    @JoinColumn(name = "id_tipo_bilhetes")
    private TipoBilhetes id_tipo_bilhetes;

    public Integer getId_bilhete() {
        return id_bilhete;
    }

    public void setId_bilhete(Integer id_bilhete) {
        this.id_bilhete = id_bilhete;
    }

    public Float getPrecototal() {
        return precototal;
    }

    public void setPrecototal(Float precototal) {
        this.precototal = precototal;
    }

    public Integer getNum_bilhetes() {
        return num_bilhetes;
    }

    public void setNum_bilhetes(Integer num_bilhetes) {
        this.num_bilhetes = num_bilhetes;
    }

    public Integer getBilhetes_disp() {
        return bilhetes_disp;
    }

    public void setBilhetes_disp(Integer bilhetes_disp) {
        this.bilhetes_disp = bilhetes_disp;
    }

    public Integer getBilhetes_comprados() {
        return bilhetes_comprados;
    }

    public void setBilhetes_comprados(Integer bilhetes_comprados) {
        this.bilhetes_comprados = bilhetes_comprados;
    }

    public Eventos getId_evento() {
        return eventos;
    }

    public void setId_evento(Eventos id_evento) {
        this.eventos = id_evento;
    }

    public TipoBilhetes getId_tipo_bilhetes() {
        return id_tipo_bilhetes;
    }

    public void setId_tipo_bilhetes(TipoBilhetes id_tipo_bilhetes) {
        this.id_tipo_bilhetes = id_tipo_bilhetes;
    }
}
