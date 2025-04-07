package com.example.boot_schedule_ver2.dto;

import lombok.Getter;

/**
 *  일정 수정 요청(Request) 데이터를 담는 DTO 클래스
 *  @Getter: Lombok 어노테이션으로, 클래스의 모든 필드에 대한 Getter 메서드를 자동으로 생성합니다.
 */
@Getter
public class UpdateScheduleRequestDto {

    private final String todoTitle; // 수정할 할 일 제목

    private final String todoContents; // 수정할 할 일 내용

    /**
     *  생성자
     *  - UpdateScheduleRequestDto 객체를 생성할 때 사용됩니다.
     *  - final 로 선언된 필드는 반드시 생성자를 통해 초기화되어야 합니다.
     *  @param todoTitle 수정할 할 일 제목
     *  @param todoContents 수정할 할 일 내용
     */
    public UpdateScheduleRequestDto(String todoTitle, String todoContents) {
        this.todoTitle = todoTitle;
        this.todoContents = todoContents;
    }
}
