package com.example.boot_schedule_ver2.service;

import com.example.boot_schedule_ver2.dto.SignUpUserResponseDto;
import com.example.boot_schedule_ver2.entity.User;
import com.example.boot_schedule_ver2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public SignUpUserResponseDto signUp(String userName, String email) {

        User user = new User(userName, email);

        User savedUser = userRepository.save(user);

        return new SignUpUserResponseDto(savedUser.getId(), savedUser.getUserName(), savedUser.getEmail());
    }

}
