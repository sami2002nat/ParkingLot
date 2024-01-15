package com.parkinglot.parkinglot.common;

public class UserDto {
    Long id;
    String email;
    String username;

    public UserDto(Long id, String email, String username) {
        this.id = id;
        this.email = email;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}