package com.example.boot_schedule_ver2.dto;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 *  회원 가입 응답(Response) 데이터를 담는 DTO 클래스
 *  @Getter: Lombok 어노테이션으로, 클래스의 모든 필드에 대한 Getter 메서드를 자동으로 생성합니다.
 */
@Getter
public class SignUpUserResponseDto {
    private final Long id; // 사용자 ID

    private final String userName; // 사용자 이름

    private final String email; // 이메일 주소

    private final String password; // 비밀번호 (주의: 실제 서비스에서는 비밀번호를 응답으로 반환하지 않는 것이 좋습니다.)

    private final LocalDateTime createDaytime; // 생성 날짜 및 시간

    private final LocalDateTime updateDaytime; // 수정 날짜 및 시간

    /**
     *  생성자
     *  - SignUpUserResponseDto 객체를 생성할 때 사용됩니다.
     *  - final 로 선언된 필드는 반드시 생성자를 통해 초기화되어야 합니다.
     *  @param id 사용자 ID
     *  @param userName 사용자 이름
     *  @param email 이메일 주소
     *  @param password 비밀번호 (주의: 실제 서비스에서는 비밀번호를 응답으로 반환하지 않는 것이 좋습니다.)
     *  @param createDaytime 생성 날짜 및 시간
     *  @param updateDaytime 수정 날짜 및 시간
     */
    public SignUpUserResponseDto(Long id, String userName, String email, String password, LocalDateTime createDaytime, LocalDateTime updateDaytime) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.createDaytime = createDaytime;
        this.updateDaytime = updateDaytime;
    }
}
