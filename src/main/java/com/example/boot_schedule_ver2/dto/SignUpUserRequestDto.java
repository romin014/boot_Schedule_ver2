package com.example.boot_schedule_ver2.dto;

import lombok.Getter;

/**
 *  회원 가입 요청(Request) 데이터를 담는 DTO 클래스
 *  @Getter: Lombok 어노테이션으로, 클래스의 모든 필드에 대한 Getter 메서드를 자동으로 생성합니다.
 */
@Getter
public class SignUpUserRequestDto {

    private final String userName; // 사용자 이름

    private final String email; // 이메일 주소

    private final String password; // 비밀번호

    /**
     *  생성자
     *  - SignUpUserRequestDto 객체를 생성할 때 사용됩니다.
     *  - final 로 선언된 필드는 반드시 생성자를 통해 초기화되어야 합니다.
     *  @param userName 사용자 이름
     *  @param email 이메일 주소
     *  @param password 비밀번호
     */
    public SignUpUserRequestDto(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}
