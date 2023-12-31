package com.example.projeto2.repository;

import com.example.projeto2.models.Compras;
import com.example.projeto2.models.TipoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComprasRepository extends JpaRepository<Compras, TipoUser> {

    @Query("SELECT c FROM Compras c WHERE c.id_bilhete.eventos.id_evento = :id_evento")
    List<Compras> findByEventoId(@Param("id_evento") Integer id_evento);

    @Query("SELECT c FROM Compras c WHERE c.id_user.id = :id_user")
    List<Compras> findByUserId(@Param("id_user") Integer id_user);

}

