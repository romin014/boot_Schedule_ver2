package com.example.boot_schedule_ver2.dto;

import lombok.Getter;

@Getter
public class SignUpUserRequestDto {

    private final String userName;

    private final String email;

    public SignUpUserRequestDto(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }
}