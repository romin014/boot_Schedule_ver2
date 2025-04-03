package com.example.boot_schedule_ver2.dto;

import com.example.boot_schedule_ver2.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private final Long id;

    private final String userName;

    private final String todoTitle;

    private final String todoContents;

    private final LocalDateTime createDaytime;

    private final LocalDateTime updateDaytime;

    public ScheduleResponseDto(Long id, String userName, String todoTitle, String todoContents, LocalDateTime createDaytime, LocalDateTime updateDaytime) {
        this.id = id;
        this.userName = userName;
        this.todoTitle = todoTitle;
        this.todoContents = todoContents;
        this.createDaytime = createDaytime;
        this.updateDaytime = updateDaytime;
    }

    public static ScheduleResponseDto toDto(Schedule schedule) {
        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getUserName(),
                schedule.getTodoTitle(),
                schedule.getTodoContents(),
                schedule.getCreateDaytime(),
                schedule.getUpdateDaytime()
        );
    }
}
