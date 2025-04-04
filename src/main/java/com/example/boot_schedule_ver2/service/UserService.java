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

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public SignUpUserResponseDto signUp(String userName, String email, String password) {

        User user = new User(userName, email, password);

        User savedUser = userRepository.save(user);

        return new SignUpUserResponseDto(
                savedUser.getId(),
                savedUser.getUserName(),
                savedUser.getEmail(),
                null,
                savedUser.getCreateDaytime(),
                savedUser.getUpdateDaytime()
        );
    }

    public SignUpUserResponseDto login(SignUpUserRequestDto request) {

        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        User user = optionalUser.orElseThrow(() -> new IllegalArgumentException("이메일 주소가 존재하지 않습니다."));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return new SignUpUserResponseDto(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                null,
                user.getCreateDaytime(),
                user.getUpdateDaytime()
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

        User findUser = optionalUser.get();

        return new UserResponseDto(
                findUser.getId(),
                findUser.getUserName(),
                findUser.getEmail(),
                findUser.getCreateDaytime(),
                findUser.getUpdateDaytime()
                );
    }

    @Transactional
    public void update(Long id, UpdateUserEmailRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 데이터가 없습니다. id=" + id));

        User findUser = userRepository.findByIdOrElseThrow(id);

        if (!user.getPassword().equals(requestDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        user.update(requestDto.getEmail());
    }

    public void delete(Long id) {

        User findUser = userRepository.findByIdOrElseThrow(id);

        userRepository.delete(findUser);
    }

}

