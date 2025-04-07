package com.example.boot_schedule_ver2.entity;

import jakarta.persistence.*;
import lombok.Getter;

/**
 *  일정(Schedule) 엔티티 클래스
 *  @Getter: Lombok 어노테이션으로, 클래스의 모든 필드에 대한 Getter 메서드를 자동으로 생성합니다.
 *  @Entity: JPA 엔티티 클래스임을 나타내는 어노테이션입니다.
 *  @Table(name = "schedule"): 데이터베이스 테이블 이름을 "schedule"로 지정하는 어노테이션입니다.
 */
@Getter
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 일정 ID (Primary Key, 자동 생성)

    @Column(nullable = false)
    private String userName; // 사용자 이름 (필수)

    @Column(nullable = false)
    private String todoTitle; // 할 일 제목 (필수)

    @Column(columnDefinition = "longtext")
    private String todoContents; // 할 일 내용 (길이 제한 없음)

    @ManyToOne // Schedule : User = N : 1 관계
    @JoinColumn(name = "user_id") // 외래 키 (Foreign Key) 컬럼 명 지정
    private User user; // 해당 일정을 작성한 사용자 (User 엔티티와 연관 관계)

    /**
     *  기본 생성자
     *  - JPA 는 엔티티 클래스에 기본 생성자가 반드시 필요합니다.
     *  - Lombok 의 @NoArgsConstructor 어노테이션을 사용하면 자동으로 기본 생성자를 생성해줍니다.
     */
    public Schedule(){

    }

    /**
     *  생성자
     *  - Schedule 객체를 생성할 때 사용됩니다.
     *  @param userName 사용자 이름
     *  @param todoTitle 할 일 제목
     *  @param todoContents 할 일 내용
     */
    public Schedule(String userName, String todoTitle, String todoContents) {
        this.userName = userName;
        this.todoTitle = todoTitle;
        this.todoContents = todoContents;
    }

    /**
     *  할 일 내용 수정 메서드
     *  - Schedule 객체의 할 일 제목과 내용을 수정합니다.
     *  @param todoTitle 수정할 할 일 제목
     *  @param todoContents 수정할 할 일 내용
     */
    public void update(String todoTitle, String todoContents) {
        this.todoTitle = todoTitle;
        this.todoContents = todoContents;
    }

}
