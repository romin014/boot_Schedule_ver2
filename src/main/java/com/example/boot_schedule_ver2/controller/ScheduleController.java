package com.example.boot_schedule_ver2.controller;

import com.example.boot_schedule_ver2.dto.ScheduleRequestDto;
import com.example.boot_schedule_ver2.dto.ScheduleResponseDto;
import com.example.boot_schedule_ver2.dto.UpdateScheduleRequestDto;
import com.example.boot_schedule_ver2.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  @RestController:
 *  - 이 클래스가 REST 컨트롤러임을 나타내는 어노테이션입니다.
 *  - @Controller 와 @ResponseBody 를 결합한 형태로, 메서드 반환 값을 HTTP 응답 본문으로 직접 전송합니다.
 *
 *  @RequestMapping("/schedules"):
 *  - 이 컨트롤러의 기본 URL 경로를 "/schedules" 로 설정합니다.
 *  - 모든 엔드포인트는 이 경로를 기준으로 정의됩니다.
 *
 *  @RequiredArgsConstructor:
 *  - Lombok 어노테이션으로, final 필드나 @NonNull 어노테이션이 붙은 필드를 파라미터로 가지는 생성자를 자동으로 생성합니다.
 *  - 여기서는 ScheduleService 를 주입받기 위한 생성자를 자동으로 생성합니다.
 */
@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     *  일정 등록 API
     *
     *  @PostMapping: HTTP POST 요청을 처리하는 메서드임을 나타냅니다.
     *  - 클라이언트로부터 데이터를 받아 서버에 새로운 리소스를 생성할 때 사용됩니다.
     *
     *  @RequestBody ScheduleRequestDto requestDto:
     *  - HTTP 요청 본문에 담긴 JSON 데이터를 ScheduleRequestDto 객체로 변환하여 메서드의 파라미터로 전달합니다.
     *  - 클라이언트가 전송한 데이터를 편리하게 사용할 수 있도록 해줍니다.
     *
     *  @return ResponseEntity<ScheduleResponseDto>:
     *  - HTTP 응답을 나타내는 객체입니다.
     *  - ScheduleResponseDto 객체를 담아 반환하며, HTTP 상태 코드(HttpStatus)를 함께 설정할 수 있습니다.
     *  - 여기서는 새로운 일정이 성공적으로 생성되었음을 나타내는 HttpStatus.CREATED (201) 상태 코드를 반환합니다.
     */
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody ScheduleRequestDto requestDto){
        // ScheduleService 를 통해 새로운 일정을 저장하고, 저장된 일정 정보를 ScheduleResponseDto 형태로 반환받습니다.
        ScheduleResponseDto scheduleResponseDto = scheduleService.save(requestDto.getUserName(), requestDto.getTodoTitle(), requestDto.getTodoContents());
        // ResponseEntity 를 사용하여 HTTP 응답을 생성합니다.
        // scheduleResponseDto 객체와 함께 HttpStatus.CREATED (201) 상태 코드를 반환하여, 새로운 리소스가 성공적으로 생성되었음을 알립니다.
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    /**
     *  전체 일정 조회 API
     *
     *  @GetMapping: HTTP GET 요청을 처리하는 메서드임을 나타냅니다.
     *  - 서버에서 데이터를 조회할 때 사용됩니다.
     *
     *  @return ResponseEntity<List<ScheduleResponseDto>>:
     *  - HTTP 응답을 나타내는 객체입니다.
     *  - List<ScheduleResponseDto> 객체를 담아 반환하며, HTTP 상태 코드(HttpStatus)를 함께 설정할 수 있습니다.
     *  - 여기서는 요청이 성공적으로 처리되었음을 나타내는 HttpStatus.OK (200) 상태 코드를 반환합니다.
     */
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {

        // ScheduleService 를 통해 모든 일정 목록을 조회합니다.
        List<ScheduleResponseDto> scheduleServiceList = scheduleService.findAll();

        // ResponseEntity 를 사용하여 HTTP 응답을 생성합니다.
        // 조회된 일정 목록과 함께 HttpStatus.OK (200) 상태 코드를 반환합니다.
        return new ResponseEntity<>(scheduleServiceList, HttpStatus.OK);
    }

    /**
     *  ID를 통한 일정 조회 API
     *
     *  @GetMapping("/{id}"):
     *  - HTTP GET 요청을 처리하며, URL 경로에 {id} 변수를 사용하여 특정 일정을 조회합니다.
     *  - @PathVariable 어노테이션을 사용하여 URL 경로의 {id} 값을 id 변수에 바인딩합니다.
     *
     *  @PathVariable Long id:
     *  - URL 경로에 있는 {id} 값을 Long 타입의 id 변수에 바인딩합니다.
     *  - 이 id 값을 사용하여 특정 일정을 조회합니다.
     *
     *  @return ResponseEntity<ScheduleResponseDto>:
     *  - HTTP 응답을 나타내는 객체입니다.
     *  - ScheduleResponseDto 객체를 담아 반환하며, HTTP 상태 코드(HttpStatus)를 함께 설정할 수 있습니다.
     *  - 여기서는 요청이 성공적으로 처리되었음을 나타내는 HttpStatus.OK (200) 상태 코드를 반환합니다.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id) {

        // ScheduleService 를 통해 주어진 id 에 해당하는 일정을 조회합니다.
        ScheduleResponseDto scheduleResponseDto = scheduleService.findById(id);

        // ResponseEntity 를 사용하여 HTTP 응답을 생성합니다.
        // 조회된 일정 정보와 함께 HttpStatus.OK (200) 상태 코드를 반환합니다.
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    /**
     *  ID를 통한 일정 수정 API
     *
     *  @PatchMapping("/{id}"):
     *  - HTTP PATCH 요청을 처리하며, URL 경로에 {id} 변수를 사용하여 특정 일정을 수정합니다.
     *  - @PathVariable 어노테이션을 사용하여 URL 경로의 {id} 값을 id 변수에 바인딩합니다.
     *
     *  @RequestBody UpdateScheduleRequestDto requestDto:
     *  - HTTP 요청 본문에 담긴 JSON 데이터를 UpdateScheduleRequestDto 객체로 변환하여 메서드의 파라미터로 전달합니다.
     *  - 클라이언트가 전송한 수정 데이터를 편리하게 사용할 수 있도록 해줍니다.
     *
     *  @return ResponseEntity<Void>:
     *  - HTTP 응답을 나타내는 객체입니다.
     *  - 여기서는 응답 본문에 데이터를 담지 않고, HTTP 상태 코드만 반환합니다.
     *  - 요청이 성공적으로 처리되었음을 나타내는 HttpStatus.OK (200) 상태 코드를 반환합니다.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UpdateScheduleRequestDto requestDto) {

        // ScheduleService 를 통해 주어진 id 에 해당하는 일정을 수정합니다.
        scheduleService.update(id, requestDto);

        // ResponseEntity 를 사용하여 HTTP 응답을 생성합니다.
        // 응답 본문 없이 HttpStatus.OK (200) 상태 코드를 반환합니다.
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *  ID를 통한 일정 삭제 API
     *
     *  @DeleteMapping("/{id}"):
     *  - HTTP DELETE 요청을 처리하며, URL 경로에 {id} 변수를 사용하여 특정 일정을 삭제합니다.
     *  - @PathVariable 어노테이션을 사용하여 URL 경로의 {id} 값을 id 변수에 바인딩합니다.
     *
     *  @return ResponseEntity<Void>:
     *  - HTTP 응답을 나타내는 객체입니다.
     *  - 여기서는 응답 본문에 데이터를 담지 않고, HTTP 상태 코드만 반환합니다.
     *  - 요청이 성공적으로 처리되었음을 나타내는 HttpStatus.OK (200) 상태 코드를 반환합니다.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        // ScheduleService 를 통해 주어진 id 에 해당하는 일정을 삭제합니다.
        scheduleService.delete(id);

        // ResponseEntity 를 사용하여 HTTP 응답을 생성합니다.
        // 응답 본문 없이 HttpStatus.OK (200) 상태 코드를 반환합니다.
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
