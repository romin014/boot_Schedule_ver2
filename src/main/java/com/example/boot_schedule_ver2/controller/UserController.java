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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody SignUpUserRequestDto requestDto) {
        try {
            SignUpUserResponseDto responseDto = userService.login(requestDto);

            session.setAttribute("userId", responseDto.getId());
            session.setAttribute("username", responseDto.getUserName());

            return ResponseEntity.ok("로그인 되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<SignUpUserResponseDto> logout() {
        session.invalidate();
        SignUpUserResponseDto responseDto = new SignUpUserResponseDto(null, "Logout", "Logout successful", null, null);
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
