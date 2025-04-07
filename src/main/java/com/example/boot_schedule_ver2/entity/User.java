package com.example.boot_schedule_ver2.entity;

import jakarta.persistence.*;
import lombok.Getter;

/**
 *  사용자(User) 엔티티 클래스
 *  @Getter: Lombok 어노테이션으로, 클래스의 모든 필드에 대한 Getter 메서드를 자동으로 생성합니다.
 *  @Entity: JPA 엔티티 클래스임을 나타내는 어노테이션입니다.
 *  @Table(name = "user"): 데이터베이스 테이블 이름을 "user"로 지정하는 어노테이션입니다.
 */
@Getter
@Entity
@Table(name = "user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 사용자 ID (Primary Key, 자동 생성)

    @Column(nullable = false)
    private String userName; // 사용자 이름 (필수)

    @Column(nullable = false, unique = true)
    private String email; // 이메일 주소 (필수, Unique 제약 조건)

    @Column(nullable = false)
    private String password; // 비밀번호 (필수)

    /**
     *  기본 생성자
     *  - JPA 는 엔티티 클래스에 기본 생성자가 반드시 필요합니다.
     *  - Lombok 의 @NoArgsConstructor 어노테이션을 사용하면 자동으로 기본 생성자를 생성해줍니다.
     */
    public User() {
    }

    /**
     *  생성자
     *  - User 객체를 생성할 때 사용됩니다.
     *  @param userName 사용자 이름
     *  @param email 이메일 주소
     *  @param password 비밀번호
     */
    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    /**
     *  이메일 주소 수정 메서드
     *  - User 객체의 이메일 주소를 수정합니다.
     *  @param email 수정할 이메일 주소
     */
    public void update(String email){
        this.email = email;
    }

}
