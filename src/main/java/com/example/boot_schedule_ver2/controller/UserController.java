package com.example.boot_schedule_ver2.controller;

import com.example.boot_schedule_ver2.dto.SignUpUserRequestDto;
import com.example.boot_schedule_ver2.dto.SignUpUserResponseDto;
import com.example.boot_schedule_ver2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpUserResponseDto> signUp(@RequestBody SignUpUserRequestDto requestDto) {

        SignUpUserResponseDto signUpUserResponseDto =
                userService.signUp(
                        requestDto.getUserName(),
                        requestDto.getEmail()
                );

        return new ResponseEntity<>(signUpUserResponseDto, HttpStatus.CREATED);
    }

}
