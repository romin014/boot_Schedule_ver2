package com.example.boot_schedule_ver2.service;

import com.example.boot_schedule_ver2.dto.ScheduleResponseDto;
import com.example.boot_schedule_ver2.dto.UpdateScheduleRequestDto;
import com.example.boot_schedule_ver2.entity.Schedule;
import com.example.boot_schedule_ver2.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto save(String userName, String todoTitle, String todoContents){
        Schedule schedule = new Schedule(userName, todoTitle, todoContents);

        scheduleRepository.save(schedule);

        //만들어진 일정의 id,작성자명, 할일 제목, 할일 내용, 작성일, 수정일 출력
        return new ScheduleResponseDto(schedule.getId(), schedule.getUserName(), schedule.getTodoTitle(), schedule.getTodoContents(), schedule.getCreateDaytime(), schedule.getUpdateDaytime());
    }

    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
    }

    public ScheduleResponseDto findById(Long id) {
        Schedule findBoard = scheduleRepository.findByIdOrElseThrow(id);

        return new ScheduleResponseDto(
                findBoard.getId(),
                findBoard.getUserName(),
                findBoard.getTodoTitle(),
                findBoard.getTodoContents(),
                findBoard.getCreateDaytime(),
                findBoard.getUpdateDaytime()
        );
    }

    @Transactional
    public void update(Long id, UpdateScheduleRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 일정이 없습니다. id=" + id));

        schedule.update(requestDto.getTodoTitle(), requestDto.getTodoContents());
    }

    public void delete(Long id) {

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        scheduleRepository.delete(findSchedule);
    }
}
