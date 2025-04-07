package com.example.boot_schedule_ver2.repository;

import com.example.boot_schedule_ver2.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 *  일정(Schedule) Repository 인터페이스
 *  - 데이터베이스에 접근하여 Schedule 엔티티 관련 작업을 수행합니다.
 *  - JpaRepository 인터페이스를 상속받아 기본적인 CRUD (Create, Read, Update, Delete) 기능을 제공받습니다.
 */
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    /**
     *  ID로 Schedule 엔티티 조회 (없을 경우 예외 발생)
     *  - findById() 메서드를 사용하여 Schedule 엔티티를 조회하고, 존재하지 않으면 ResponseStatusException 예외를 발생시킵니다.
     *  - default 키워드를 사용하여 인터페이스 내에서 메서드 구현을 제공합니다.
     *  @param id 조회할 Schedule 엔티티의 ID
     *  @return 조회된 Schedule 엔티티
     *  @throws ResponseStatusException Schedule 엔티티가 존재하지 않을 경우 HttpStatus.NOT_FOUND (404) 상태 코드와 함께 예외 발생
     */
    default Schedule findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
    }
}
