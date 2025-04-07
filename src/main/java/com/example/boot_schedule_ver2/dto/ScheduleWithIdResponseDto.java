package com.example.boot_schedule_ver2.dto;

import lombok.Getter;

/**
 *  ID를 제외한 일정 정보 응답(Response) 데이터를 담는 DTO 클래스
 *  @Getter: Lombok 어노테이션으로, 클래스의 모든 필드에 대한 Getter 메서드를 자동으로 생성합니다.
 */
@Getter
public class ScheduleWithIdResponseDto {

    private final String todoTitle; // 할 일 제목

    private final String todoContents; // 할 일 내용

    /**
     *  생성자
     *  - ScheduleWithIdResponseDto 객체를 생성할 때 사용됩니다.
     *  - final 로 선언된 필드는 반드시 생성자를 통해 초기화되어야 합니다.
     *  @param todoTitle 할 일 제목
     *  @param todoContents 할 일 내용
     */
    public ScheduleWithIdResponseDto(String todoTitle, String todoContents) {
        this.todoTitle = todoTitle;
        this.todoContents = todoContents;
    }
}
