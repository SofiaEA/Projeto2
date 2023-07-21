package com.example.projeto2.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "users")
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_user;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Integer telemovel;
    private UserRole userRole;
    @OneToOne
    @JoinColumn(name = "id_tipo_user")
    private TipoUser tipo_user;

    public User(String login, String password, UserRole role){
        this.username = login;
        this.password = password;
        this.userRole = role;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.userRole == UserRole.USERMANAGER)
            return List.of(new SimpleGrantedAuthority("ROLE_USERMANAGER"),
                new SimpleGrantedAuthority("ROLE_ORGANIZADOR"),
                    new SimpleGrantedAuthority("ROLE_PARTICIPANTE"));
        if (this.userRole == UserRole.ORGANIZADOR)
            return List.of(new SimpleGrantedAuthority("ROLE_ORGANIZADOR"),
                new SimpleGrantedAuthority("ROLE_PARTICIPANTE"));
        else return List.of(new SimpleGrantedAuthority("ROLE_PARTICIPANTE"));
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(Integer telemovel) {
        this.telemovel = telemovel;
    }

    public TipoUser getTipo_user() {
        return tipo_user;
    }

    public void setTipo_user(TipoUser tipo_user) {
        this.tipo_user = tipo_user;
    }
}