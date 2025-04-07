package com.example.boot_schedule_ver2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 *  엔티티의 생성 시간, 수정 시간을 자동으로 관리해주는 추상 클래스
 *  @Getter: Lombok 어노테이션으로, 클래스의 모든 필드에 대한 Getter 메서드를 자동으로 생성합니다.
 *  @MappedSuperclass: JPA 엔티티 클래스들이 공통으로 사용하는 필드를 정의하는 어노테이션입니다.
 *                       이 클래스를 상속받는 엔티티는 BaseEntity 의 필드를 컬럼으로 가지게 됩니다.
 *  @EntityListeners(AuditingEntityListener.class): 엔티티의 이벤트를 감지하고 특정 로직을 수행하는 Listener 를 지정하는 어노테이션입니다.
 *                                                   AuditingEntityListener 는 엔티티의 생성/수정 시 자동으로 시간을 기록하는 기능을 제공합니다.
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false) // 생성일시는 수정 불가

    private LocalDateTime createDaytime; // 생성 날짜 및 시간

    @LastModifiedDate
    private LocalDateTime updateDaytime; // 수정 날짜 및 시간
}
