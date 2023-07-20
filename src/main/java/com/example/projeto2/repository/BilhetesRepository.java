package com.example.projeto2.repository;

import com.example.projeto2.models.Bilhetes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BilhetesRepository extends JpaRepository<Bilhetes, Integer> {

    @Query("SELECT b FROM Bilhetes b WHERE b.eventos.id_evento = :id_evento")
    List<Bilhetes> findByEventoId(@Param("id_evento")Integer id_evento);

}