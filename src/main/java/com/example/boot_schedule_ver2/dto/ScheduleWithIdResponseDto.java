package com.example.boot_schedule_ver2.dto;

import lombok.Getter;

@Getter
public class ScheduleWithIdResponseDto {

    private final String todoTitle;

    private final String todoContents;

    public ScheduleWithIdResponseDto(String todoTitle, String todoContents) {
        this.todoTitle = todoTitle;
        this.todoContents = todoContents;
    }
}
