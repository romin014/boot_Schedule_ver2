package com.example.boot_schedule_ver2.dto;

import lombok.Getter;

/**
 *  사용자 이메일 수정 요청(Request) 데이터를 담는 DTO 클래스
 *  @Getter: Lombok 어노테이션으로, 클래스의 모든 필드에 대한 Getter 메서드를 자동으로 생성합니다.
 */
@Getter
public class UpdateUserEmailRequestDto {
    private final String email; // 수정할 이메일 주소

    private final String password; // 현재 비밀번호 (이메일 변경 시 인증을 위해 필요)

    /**
     *  생성자
     *  - UpdateUserEmailRequestDto 객체를 생성할 때 사용됩니다.
     *  - final 로 선언된 필드는 반드시 생성자를 통해 초기화되어야 합니다.
     *  @param email 수정할 이메일 주소
     *  @param password 현재 비밀번호
     */
    public UpdateUserEmailRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
