package com.example.projeto2.repository;

import com.example.projeto2.models.Bilhetes;
import com.example.projeto2.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BilhetesRepository extends JpaRepository<Bilhetes, Integer> {

    Optional<Bilhetes> findById(Integer id_bilhete);

    @Query("SELECT b FROM Bilhetes b WHERE b.eventos.id_evento = :id_evento")
    List<Bilhetes> findByEventoId(@Param("id_evento")Integer id_evento);

}