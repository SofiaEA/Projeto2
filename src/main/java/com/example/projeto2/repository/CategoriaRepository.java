package com.example.projeto2.repository;

import com.example.projeto2.models.Categorias;
import com.example.projeto2.models.TipoUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categorias, TipoUser> {

}

