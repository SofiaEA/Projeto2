package com.example.projeto2.repository;

import com.example.projeto2.models.Eventos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Eventos, Integer> {

    @Query(value = "SELECT * FROM Eventos e WHERE LOWER(e.nome) LIKE %:keyword% OR LOWER(e.local) LIKE %:keyword%", nativeQuery = true)
    List<Eventos> findByKeyword(@Param("keyword") String keyword);

}


