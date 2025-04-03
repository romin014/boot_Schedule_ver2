package com.example.boot_schedule_ver2.dto;

import lombok.Getter;

@Getter
public class SignUpUserResponseDto {
    private final Long id;

    private final String userName;

    private final String email;

    public SignUpUserResponseDto(Long id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
    }
}
