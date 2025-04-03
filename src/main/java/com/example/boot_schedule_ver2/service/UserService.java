package com.example.boot_schedule_ver2.service;

import com.example.boot_schedule_ver2.dto.SignUpUserResponseDto;
import com.example.boot_schedule_ver2.dto.UserResponseDto;
import com.example.boot_schedule_ver2.entity.User;
import com.example.boot_schedule_ver2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public SignUpUserResponseDto signUp(String userName, String email) {

        User user = new User(userName, email);

        User savedUser = userRepository.save(user);

        return new SignUpUserResponseDto(
                savedUser.getId(),
                savedUser.getUserName(),
                savedUser.getEmail(),
                savedUser.getCreateDaytime(),
                savedUser.getUpdateDaytime()
        );
    }

    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::toDto)
                .collect(Collectors.toList());
    }

    public UserResponseDto findById(Long id) {

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        User findUser = optionalUser.get();

        return new UserResponseDto(
                findUser.getId(),
                findUser.getUserName(),
                findUser.getEmail(),
                findUser.getCreateDaytime(),
                findUser.getUpdateDaytime()
                );
    }

}
