package com.example.boot_schedule_ver2.dto;

import lombok.Getter;

@Getter
public class UpdateUserEmailRequestDto {
    private final String email;

    public UpdateUserEmailRequestDto(String email) {
        this.email = email;
    }
}
