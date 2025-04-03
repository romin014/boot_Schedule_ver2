package com.example.boot_schedule_ver2.service;

import com.example.boot_schedule_ver2.dto.ScheduleResponseDto;
import com.example.boot_schedule_ver2.entity.Schedule;
import com.example.boot_schedule_ver2.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto save(String userName, String todoTitle, String todoContents){
        Schedule schedule = new Schedule(userName, todoTitle, todoContents);

        scheduleRepository.save(schedule);

        return new ScheduleResponseDto(schedule.getId(), schedule.getUserName(), schedule.getTodoTitle(), schedule.getTodoContents());
    }
}
