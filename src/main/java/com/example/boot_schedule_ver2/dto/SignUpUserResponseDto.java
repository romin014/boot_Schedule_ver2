package com.example.boot_schedule_ver2.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SignUpUserResponseDto {
    private final Long id;

    private final String userName;

    private final String email;

    private final String password;

    private final LocalDateTime createDaytime;

    private final LocalDateTime updateDaytime;

    public SignUpUserResponseDto(Long id, String userName, String email, String password, LocalDateTime createDaytime, LocalDateTime updateDaytime) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.createDaytime = createDaytime;
        this.updateDaytime = updateDaytime;
    }
}
