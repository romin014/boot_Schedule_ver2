package com.example.boot_schedule_ver2.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {

    private final String userName;

    private final String todoTitle;

    private final String todoContents;

    public ScheduleRequestDto(String userName, String todoTitle, String todoContents) {
        this.userName = userName;
        this.todoTitle = todoTitle;
        this.todoContents = todoContents;
    }
}
