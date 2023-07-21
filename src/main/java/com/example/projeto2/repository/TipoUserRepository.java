package com.example.projeto2.repository;

import com.example.projeto2.models.TipoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoUserRepository extends JpaRepository<TipoUser, java.lang.Integer> {

}

