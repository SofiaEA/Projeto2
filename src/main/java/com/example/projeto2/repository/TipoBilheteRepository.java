package com.example.projeto2.repository;

import com.example.projeto2.models.TipoBilhetes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TipoBilheteRepository extends JpaRepository<TipoBilhetes, Integer> {

}

