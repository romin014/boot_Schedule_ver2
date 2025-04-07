package com.example.boot_schedule_ver2.dto;

import com.example.boot_schedule_ver2.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 *  일정 응답(Response) 데이터를 담는 DTO 클래스
 *  @Getter: Lombok 어노테이션으로, 클래스의 모든 필드에 대한 Getter 메서드를 자동으로 생성합니다.
 */
@Getter
public class ScheduleResponseDto {
    private final Long id; // 일정 ID

    private final String userName; // 작성자 이름

    private final String todoTitle; // 할 일 제목

    private final String todoContents; // 할 일 내용

    private final LocalDateTime createDaytime; // 생성 날짜 및 시간

    private final LocalDateTime updateDaytime; // 수정 날짜 및 시간

    /**
     *  생성자
     *  - ScheduleResponseDto 객체를 생성할 때 사용됩니다.
     *  - final 로 선언된 필드는 반드시 생성자를 통해 초기화되어야 합니다.
     *  @param id 일정 ID
     *  @param userName 작성자 이름
     *  @param todoTitle 할 일 제목
     *  @param todoContents 할 일 내용
     *  @param createDaytime 생성 날짜 및 시간
     *  @param updateDaytime 수정 날짜 및 시간
     */
    public ScheduleResponseDto(Long id, String userName, String todoTitle, String todoContents, LocalDateTime createDaytime, LocalDateTime updateDaytime) {
        this.id = id;
        this.userName = userName;
        this.todoTitle = todoTitle;
        this.todoContents = todoContents;
        this.createDaytime = createDaytime;
        this.updateDaytime = updateDaytime;
    }

    /**
     *  Entity -> DTO 변환 메서드
     *  - Schedule 엔티티 객체를 ScheduleResponseDto DTO 객체로 변환합니다.
     *  - toDto 메서드는 static 으로 선언되어 있어, 객체 생성 없이 클래스 이름으로 직접 호출할 수 있습니다.
     *  @param schedule 변환할 Schedule 엔티티 객체
     *  @return ScheduleResponseDto DTO 객체
     */
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
