package com.example.boot_schedule_ver2.dto;

import lombok.Getter;

@Getter
public class SignUpUserRequestDto {

    private final String userName;

    private final String email;

    private final String password;

    public SignUpUserRequestDto(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}