package com.example.projeto2;

import com.example.projeto2.models.UserRole;

public record RegisterDTO(String username, String password, UserRole role) {
}
