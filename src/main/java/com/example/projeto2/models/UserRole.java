package com.example.projeto2.models;

public enum UserRole {
    USERMANAGER ("userManager"),
    ORGANIZADOR("organizador"),
    PARTICIPANTE("participante");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
