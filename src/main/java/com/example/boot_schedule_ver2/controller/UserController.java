package com.example.boot_schedule_ver2.controller;

import com.example.boot_schedule_ver2.dto.SignUpUserRequestDto;
import com.example.boot_schedule_ver2.dto.SignUpUserResponseDto;
import com.example.boot_schedule_ver2.dto.UpdateUserEmailRequestDto;
import com.example.boot_schedule_ver2.dto.UserResponseDto;
import com.example.boot_schedule_ver2.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  @RestController: REST 컨트롤러임을 나타냅니다.
 *  @RequestMapping("/users"): 이 컨트롤러의 기본 URL 경로를 "/users"로 설정합니다.
 *  @RequiredArgsConstructor: final 필드나 @NonNull 어노테이션이 붙은 필드를 파라미터로 가지는 생성자를 자동으로 생성합니다.
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final HttpSession session;

    /**
     *  로그인 API
     *  @PostMapping("/login"): HTTP POST 요청을 처리하며, URL 경로는 "/users/login" 입니다.
     *  @RequestBody SignUpUserRequestDto requestDto: 요청 본문에 담긴 JSON 데이터를 SignUpUserRequestDto 객체로 변환하여 받습니다.
     *  @return ResponseEntity<String>: HTTP 응답으로 문자열을 반환합니다.
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody SignUpUserRequestDto requestDto) {
        // SignUpUserRequestDto를 통해 로그인 요청을 받습니다.
        try {
            // UserService를 통해 유저 인증을 시도합니다.
            SignUpUserResponseDto responseDto = userService.login(requestDto);

            // 인증 성공 시, 세션에 사용자의 이메일과 비밀번호를 저장합니다.
            session.setAttribute("email", responseDto.getEmail());
            session.setAttribute("password", responseDto.getPassword());

            // 로그인 성공 메시지를 반환합니다.
            return ResponseEntity.ok("로그인 되었습니다.");
        } catch (IllegalArgumentException e) {
            // 인증 실패 시, Unauthorized (401) 상태 코드와 함께 에러 메시지를 반환합니다.
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    /**
     *  로그아웃 API
     *  @PostMapping("/logout"): HTTP POST 요청을 처리하며, URL 경로는 "/users/logout" 입니다.
     *  @return ResponseEntity<SignUpUserResponseDto>: HTTP 응답으로 SignUpUserResponseDto 객체를 반환합니다.
     */
    @PostMapping("/logout")
    public ResponseEntity<SignUpUserResponseDto> logout() {
        // 세션을 무효화하여 로그아웃 처리합니다.
        session.invalidate();

        // 로그아웃 성공 메시지를 담은 SignUpUserResponseDto 객체를 생성합니다.
        SignUpUserResponseDto responseDto = new SignUpUserResponseDto(null, "Logout", "Logout successful", null, null, null);
        // 로그아웃 성공 응답을 반환합니다.
        return ResponseEntity.ok(responseDto);
    }

    /**
     *  회원 가입 API
     *  @PostMapping: HTTP POST 요청을 처리하며, URL 경로는 "/users" 입니다.
     *  @RequestBody SignUpUserRequestDto requestDto: 요청 본문에 담긴 JSON 데이터를 SignUpUserRequestDto 객체로 변환하여 받습니다.
     *  @return ResponseEntity<SignUpUserResponseDto>: HTTP 응답으로 SignUpUserResponseDto 객체를 반환합니다.
     */
    @PostMapping
    public ResponseEntity<SignUpUserResponseDto> signUp(@RequestBody SignUpUserRequestDto requestDto) {

        // UserService를 통해 회원 가입을 처리하고, 결과를 SignUpUserResponseDto 객체로 반환받습니다.
        SignUpUserResponseDto signUpUserResponseDto =
                userService.signUp(
                        requestDto.getUserName(),
                        requestDto.getEmail(),
                        requestDto.getPassword()
                );

        // 회원 가입 성공 응답을 반환합니다.
        return new ResponseEntity<>(signUpUserResponseDto, HttpStatus.CREATED);
    }

    /**
     *  전체 유저 조회 API
     *  @GetMapping: HTTP GET 요청을 처리하며, URL 경로는 "/users" 입니다.
     *  @return ResponseEntity<List<UserResponseDto>>: HTTP 응답으로 UserResponseDto 객체의 리스트를 반환합니다.
     */
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {

        // UserService를 통해 모든 유저 목록을 조회합니다.
        List<UserResponseDto> userResponseDtoList = userService.findAll();

        // 유저 목록 조회 성공 응답을 반환합니다.
        return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
    }

    /**
     *  ID를 통한 유저 조회 API
     *  @GetMapping("/{id}"): HTTP GET 요청을 처리하며, URL 경로는 "/users/{id}" 입니다.
     *  @PathVariable Long id: URL 경로의 {id} 값을 Long 타입의 id 변수에 바인딩합니다.
     *  @return ResponseEntity<UserResponseDto>: HTTP 응답으로 UserResponseDto 객체를 반환합니다.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {

        // UserService를 통해 주어진 id에 해당하는 유저를 조회합니다.
        UserResponseDto userResponseDto = userService.findById(id);

        // 유저 조회 성공 응답을 반환합니다.
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    /**
     *  ID를 통한 유저 정보 수정 API (이메일)
     *  @PatchMapping("/{id}"): HTTP PATCH 요청을 처리하며, URL 경로는 "/users/{id}" 입니다.
     *  @PathVariable Long id: URL 경로의 {id} 값을 Long 타입의 id 변수에 바인딩합니다.
     *  @RequestBody UpdateUserEmailRequestDto requestDto: 요청 본문에 담긴 JSON 데이터를 UpdateUserEmailRequestDto 객체로 변환하여 받습니다.
     *  @return ResponseEntity<Void>: HTTP 응답으로 아무런 데이터를 반환하지 않습니다.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UpdateUserEmailRequestDto requestDto) {

        // UserService를 통해 주어진 id에 해당하는 유저의 이메일을 수정합니다.
        userService.update(id, requestDto);

        // 유저 정보 수정 성공 응답을 반환합니다.
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *  ID를 통한 유저 삭제 API
     *  @DeleteMapping("/{id}"): HTTP DELETE 요청을 처리하며, URL 경로는 "/users/{id}" 입니다.
     *  @PathVariable Long id: URL 경로의 {id} 값을 Long 타입의 id 변수에 바인딩합니다.
     *  @return ResponseEntity<Void>: HTTP 응답으로 아무런 데이터를 반환하지 않습니다.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        // UserService를 통해 주어진 id에 해당하는 유저를 삭제합니다.
        userService.delete(id);

        // 유저 삭제 성공 응답을 반환합니다.
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
