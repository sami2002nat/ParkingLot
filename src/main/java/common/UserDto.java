package com.parking.parkinglot.common;

import jakarta.persistence.Basic;

public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;

    public UserDto(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}