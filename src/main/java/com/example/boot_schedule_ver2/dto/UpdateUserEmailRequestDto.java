package com.example.boot_schedule_ver2.dto;

import lombok.Getter;

@Getter
public class UpdateUserEmailRequestDto {
    private final String email;

    private final String password;

    public UpdateUserEmailRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
