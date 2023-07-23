package com.example.projeto2.models;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum UserRole {
    MANAGER("manager"),
    ORGANIZER("organizer"),
    PARTICIPANT("participant");
    @Enumerated(EnumType.STRING)
    private final String role;

    UserRole(String role) {

        this.role = role;
    }


    public String getRole() {
        return role;
    }
}
