package com.telran.a16_03_20.data.dto;

import com.google.gson.annotations.SerializedName;

public class ErrorResponseDto {
    int code;
    String details;
    String message;

    public ErrorResponseDto() {
    }

    public ErrorResponseDto(int code, String details, String message) {
        this.code = code;
        this.details = details;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getDetails() {
        return details;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorResponseDto{" +
                "code=" + code +
                ", details='" + details + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
