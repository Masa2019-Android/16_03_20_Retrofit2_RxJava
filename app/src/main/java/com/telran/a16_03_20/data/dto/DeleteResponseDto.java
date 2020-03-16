package com.telran.a16_03_20.data.dto;

public class DeleteResponseDto {
    String status;

    public DeleteResponseDto() {
    }

    public DeleteResponseDto(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "DeleteResponseDto{" +
                "status='" + status + '\'' +
                '}';
    }
}
