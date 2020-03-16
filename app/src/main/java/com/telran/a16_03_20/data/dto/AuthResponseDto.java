package com.telran.a16_03_20.data.dto;

public class AuthResponseDto {
    String token;

    public AuthResponseDto() {
    }

    public AuthResponseDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "AuthResponseDto{" +
                "token='" + token + '\'' +
                '}';
    }
}
