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

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final HttpSession session;

    //인증에 성공하면:
    //세션에 사용자 ID와 사용자 이름을 저장합니다.
    //HTTP 상태 코드 200 (OK)과 함께 "로그인 되었습니다." 메시지를 반환합니다.
    //인증에 실패하면 (IllegalArgumentException 발생 시):
    //HTTP 상태 코드 401 (Unauthorized)과 함께 오류 메시지를 반환합니다.
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody SignUpUserRequestDto requestDto) {
        //SignUpUserRequestDto를 통해 로그인 요청받음
        try {
            //유저 인증
            SignUpUserResponseDto responseDto = userService.login(requestDto);

            //세션에 사용자의 이메일, 비밀번호 저장
            session.setAttribute("email", responseDto.getEmail());
            session.setAttribute("password", responseDto.getPassword());

            return ResponseEntity.ok("로그인 되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<SignUpUserResponseDto> logout() {
        //세션 무효화
        session.invalidate();

        SignUpUserResponseDto responseDto = new SignUpUserResponseDto(null, "Logout", "Logout successful", null, null, null);
        return ResponseEntity.ok(responseDto);
    }


    @PostMapping
    public ResponseEntity<SignUpUserResponseDto> signUp(@RequestBody SignUpUserRequestDto requestDto) {

        SignUpUserResponseDto signUpUserResponseDto =
                userService.signUp(
                        requestDto.getUserName(),
                        requestDto.getEmail(),
                        requestDto.getPassword()
                );

        return new ResponseEntity<>(signUpUserResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {

        List<UserResponseDto> userResponseDtoList = userService.findAll();

        return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {

        UserResponseDto userResponseDto = userService.findById(id);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UpdateUserEmailRequestDto requestDto) {

        userService.update(id, requestDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        userService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
