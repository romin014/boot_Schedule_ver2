package com.example.boot_schedule_ver2.repository;

import com.example.boot_schedule_ver2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //입력받은 id 값에 맞는 User값 반환
    //orElseThrow를 통해 반환값이 없다면 오류 메시지 출력
    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
    }
    //입력받은 email을 통해 데이터 베이스에서 해당하는 User값 반환
    Optional<User> findByEmail(String email);
//    default User findByEmailOrElseThrow(String email){
//        return optionalUser(email).orElseThrow(()-> new IllegalArgumentException("이메일 주소가 존재하지 않습니다."));
//    }

}
