package com.example.boot_schedule_ver2.service;

import com.example.boot_schedule_ver2.dto.ScheduleResponseDto;
import com.example.boot_schedule_ver2.dto.UpdateScheduleRequestDto;
import com.example.boot_schedule_ver2.entity.Schedule;
import com.example.boot_schedule_ver2.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  일정(Schedule) 서비스 클래스
 *  - 일정 생성, 조회, 수정, 삭제 등의 비즈니스 로직을 처리합니다.
 *  @Service: Spring Service 컴포넌트임을 나타내는 어노테이션입니다.
 *  @RequiredArgsConstructor: Lombok 어노테이션으로, final 또는 @NonNull 필드에 대한 생성자를 자동으로 생성합니다.
 */
@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository; // ScheduleRepository 주입

    /**
     *  일정 저장
     *  @param userName 사용자 이름
     *  @param todoTitle 할 일 제목
     *  @param todoContents 할 일 내용
     *  @return 저장된 일정 정보를 담은 ScheduleResponseDto
     */
    public ScheduleResponseDto save(String userName, String todoTitle, String todoContents){
        Schedule schedule = new Schedule(userName, todoTitle, todoContents); // Schedule 엔티티 생성

        scheduleRepository.save(schedule); // 데이터베이스에 저장

        // 생성된 일정 정보를 ScheduleResponseDto 로 변환하여 반환
        return new ScheduleResponseDto(schedule.getId(), schedule.getUserName(), schedule.getTodoTitle(), schedule.getTodoContents(), schedule.getCreateDaytime(), schedule.getUpdateDaytime());
    }

    /**
     *  모든 일정 조회
     *  @return 모든 일정 정보를 담은 ScheduleResponseDto 리스트
     */
    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll() // 모든 Schedule 엔티티 조회
                .stream() // Stream API 사용
                .map(ScheduleResponseDto::toDto) // 각 Schedule 엔티티를 ScheduleResponseDto 로 변환
                .toList(); // List 로 변환
    }

    /**
     *  ID로 특정 일정 조회
     *  @param id 조회할 일정 ID
     *  @return 조회된 일정 정보를 담은 ScheduleResponseDto
     */
    public ScheduleResponseDto findById(Long id) {
        Schedule findBoard = scheduleRepository.findByIdOrElseThrow(id); // ID로 Schedule 엔티티 조회 (없으면 예외 발생)

        // 조회된 일정 정보를 ScheduleResponseDto 로 변환하여 반환
        return new ScheduleResponseDto(
                findBoard.getId(),
                findBoard.getUserName(),
                findBoard.getTodoTitle(),
                findBoard.getTodoContents(),
                findBoard.getCreateDaytime(),
                findBoard.getUpdateDaytime()
        );
    }

    /**
     *  일정 수정
     *  @param id 수정할 일정 ID
     *  @param requestDto 수정할 내용을 담은 UpdateScheduleRequestDto
     */
    @Transactional // 트랜잭션 처리
    public void update(Long id, UpdateScheduleRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(id) // ID로 Schedule 엔티티 조회
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 일정이 없습니다. id=" + id)); // 없으면 예외 발생

        schedule.update(requestDto.getTodoTitle(), requestDto.getTodoContents()); // Schedule 엔티티 업데이트
    }

    /**
     *  일정 삭제
     *  @param id 삭제할 일정 ID
     */
    public void delete(Long id) {

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id); // ID로 Schedule 엔티티 조회 (없으면 예외 발생)

        scheduleRepository.delete(findSchedule); // 데이터베이스에서 삭제
    }
}
