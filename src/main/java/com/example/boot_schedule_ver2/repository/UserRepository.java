package com.example.boot_schedule_ver2.repository;

import com.example.boot_schedule_ver2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/**
 *  사용자(User) Repository 인터페이스
 *  - 데이터베이스에 접근하여 User 엔티티 관련 작업을 수행합니다.
 *  - JpaRepository 인터페이스를 상속받아 기본적인 CRUD (Create, Read, Update, Delete) 기능을 제공받습니다.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     *  ID로 User 엔티티 조회 (없을 경우 예외 발생)
     *  - findById() 메서드를 사용하여 User 엔티티를 조회하고, 존재하지 않으면 ResponseStatusException 예외를 발생시킵니다.
     *  - default 키워드를 사용하여 인터페이스 내에서 메서드 구현을 제공합니다.
     *  @param id 조회할 User 엔티티의 ID
     *  @return 조회된 User 엔티티
     *  @throws ResponseStatusException User 엔티티가 존재하지 않을 경우 HttpStatus.NOT_FOUND (404) 상태 코드와 함께 예외 발생
     */
    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
    }

    /**
     *  이메일 주소로 User 엔티티 조회
     *  - findByEmail() 메서드를 사용하여 주어진 이메일 주소와 일치하는 User 엔티티를 조회합니다.
     *  - Optional<User> 를 반환하여, 해당 이메일 주소를 가진 User 가 존재하지 않을 경우 null 을 안전하게 처리할 수 있도록 합니다.
     *  @param email 조회할 User 엔티티의 이메일 주소
     *  @return 조회된 User 엔티티 (Optional)
     */
    Optional<User> findByEmail(String email);

    // 주석 처리된 findByEmailOrElseThrow() 메서드 (예시)
    // - findByEmail() 메서드를 사용하여 User 엔티티를 조회하고, 존재하지 않으면 IllegalArgumentException 예외를 발생시킵니다.
    // - 기존 코드에서 IllegalArgumentException 을 사용했지만, 일관성을 위해 ResponseStatusException 으로 변경하는 것이 좋습니다.
//    default User findByEmailOrElseThrow(String email){
//        return findByEmail(email).orElseThrow(()-> new IllegalArgumentException("이메일 주소가 존재하지 않습니다."));
//    }
}
