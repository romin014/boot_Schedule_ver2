package com.example.boot_schedule_ver2.service;

import com.example.boot_schedule_ver2.dto.SignUpUserRequestDto;
import com.example.boot_schedule_ver2.dto.SignUpUserResponseDto;
import com.example.boot_schedule_ver2.dto.UpdateUserEmailRequestDto;
import com.example.boot_schedule_ver2.dto.UserResponseDto;
import com.example.boot_schedule_ver2.entity.User;
import com.example.boot_schedule_ver2.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *  사용자(User) 서비스 클래스
 *  - 사용자 등록, 로그인, 조회, 수정, 삭제 등의 비즈니스 로직을 처리합니다.
 *  @Service: Spring Service 컴포넌트임을 나타내는 어노테이션입니다.
 *  @RequiredArgsConstructor: Lombok 어노테이션으로, final 또는 @NonNull 필드에 대한 생성자를 자동으로 생성합니다.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository; // UserRepository 주입

    /**
     *  사용자 등록
     *  @param userName 사용자 이름
     *  @param email 이메일 주소
     *  @param password 비밀번호
     *  @return 등록된 사용자 정보를 담은 SignUpUserResponseDto
     */
    public SignUpUserResponseDto signUp(String userName, String email, String password) {

        User user = new User(userName, email, password); // User 엔티티 생성

        User savedUser = userRepository.save(user); // 데이터베이스에 저장

        // 생성된 사용자 정보를 SignUpUserResponseDto 로 변환하여 반환
        return new SignUpUserResponseDto(
                savedUser.getId(),
                savedUser.getUserName(),
                savedUser.getEmail(),
                null, // 비밀번호는 응답에 포함하지 않음
                savedUser.getCreateDaytime(),
                savedUser.getUpdateDaytime()
        );
    }

    /**
     *  사용자 로그인
     *  @param request 로그인 요청 정보를 담은 SignUpUserRequestDto
     *  @return 로그인된 사용자 정보를 담은 SignUpUserResponseDto
     *  @throws IllegalArgumentException 이메일 주소가 존재하지 않거나 비밀번호가 일치하지 않을 경우 예외 발생
     */
    public SignUpUserResponseDto login(SignUpUserRequestDto request) {

        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail()); // 이메일 주소로 User 엔티티 조회
        User user = optionalUser.orElseThrow(() -> new IllegalArgumentException("이메일 주소가 존재하지 않습니다.")); // 없으면 예외 발생

        if (!user.getPassword().equals(request.getPassword())) { // 비밀번호 일치 여부 확인
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다."); // 일치하지 않으면 예외 발생
        }

        // 로그인된 사용자 정보를 SignUpUserResponseDto 로 변환하여 반환
        return new SignUpUserResponseDto(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                null, // 비밀번호는 응답에 포함하지 않음
                user.getCreateDaytime(),
                user.getUpdateDaytime()
        );
    }

    /**
     *  모든 사용자 조회
     *  @return 모든 사용자 정보를 담은 UserResponseDto 리스트
     */
    public List<UserResponseDto> findAll() {
        return userRepository.findAll() // 모든 User 엔티티 조회
                .stream() // Stream API 사용
                .map(UserResponseDto::toDto) // 각 User 엔티티를 UserResponseDto 로 변환
                .collect(Collectors.toList()); // List 로 변환
    }

    /**
     *  ID로 특정 사용자 조회
     *  @param id 조회할 사용자 ID
     *  @return 조회된 사용자 정보를 담은 UserResponseDto
     */
    public UserResponseDto findById(Long id) {

        Optional<User> optionalUser = userRepository.findById(id); // ID로 User 엔티티 조회

        User findUser = optionalUser.get(); // User 엔티티 가져오기 (Optional 에서)

        // 조회된 사용자 정보를 UserResponseDto 로 변환하여 반환
        return new UserResponseDto(
                findUser.getId(),
                findUser.getUserName(),
                findUser.getEmail(),
                findUser.getCreateDaytime(),
                findUser.getUpdateDaytime()
        );
    }

    /**
     *  사용자 정보 수정 (이메일 주소)
     *  @param id 수정할 사용자 ID
     *  @param requestDto 수정할 이메일 주소와 비밀번호를 담은 UpdateUserEmailRequestDto
     *  @throws ResponseStatusException 비밀번호가 일치하지 않을 경우 401 Unauthorized 예외 발생
     */
    @Transactional // 트랜잭션 처리
    public void update(Long id, UpdateUserEmailRequestDto requestDto) {
        User user = userRepository.findById(id) // ID로 User 엔티티 조회
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 데이터가 없습니다. id=" + id)); // 없으면 예외 발생

        User findUser = userRepository.findByIdOrElseThrow(id); // ID로 User 엔티티 조회 (없으면 예외 발생)

        if (!user.getPassword().equals(requestDto.getPassword())) { // 비밀번호 일치 여부 확인
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."); // 일치하지 않으면 401 예외 발생
        }
        user.update(requestDto.getEmail()); // User 엔티티 업데이트 (이메일 주소)
    }

    /**
     *  사용자 삭제
     *  @param id 삭제할 사용자 ID
     */
    public void delete(Long id) {

        User findUser = userRepository.findByIdOrElseThrow(id); // ID로 User 엔티티 조회 (없으면 예외 발생)

        userRepository.delete(findUser); // 데이터베이스에서 삭제
    }

}
