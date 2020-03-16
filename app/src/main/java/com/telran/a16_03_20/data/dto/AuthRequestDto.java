package com.telran.a16_03_20.data.dto;

public class AuthRequestDto {
    String email;
    String password;

    public AuthRequestDto() {
    }

    public AuthRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "AuthRequestDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
