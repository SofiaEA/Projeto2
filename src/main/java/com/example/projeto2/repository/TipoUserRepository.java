package com.example.projeto2.repository;

import com.example.projeto2.models.TipoUser;
import com.example.projeto2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoUserRepository extends JpaRepository<TipoUser, Integer> {

}

