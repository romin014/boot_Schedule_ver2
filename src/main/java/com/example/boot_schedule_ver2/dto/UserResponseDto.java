package com.example.boot_schedule_ver2.dto;

import com.example.boot_schedule_ver2.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {
    private Long id;

    private final String userName;

    private final String email;

    private final LocalDateTime createDaytime;

    private final LocalDateTime updateDaytime;


    public UserResponseDto(Long id, String userName, String email, LocalDateTime createDaytime, LocalDateTime updateDaytime) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.createDaytime = createDaytime;
        this.updateDaytime = updateDaytime;
    }

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreateDaytime(),
                user.getUpdateDaytime()
        );
    }
}

