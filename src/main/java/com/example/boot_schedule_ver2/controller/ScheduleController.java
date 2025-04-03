package com.example.boot_schedule_ver2.controller;

import com.example.boot_schedule_ver2.dto.ScheduleRequestDto;
import com.example.boot_schedule_ver2.dto.ScheduleResponseDto;
import com.example.boot_schedule_ver2.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody ScheduleRequestDto requestDto){
        ScheduleResponseDto scheduleResponseDto = scheduleService.save(requestDto.getUserName(), requestDto.getTodoTitle(), requestDto.getTodoContents());
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {

        List<ScheduleResponseDto> scheduleServiceList = scheduleService.findAll();

        return new ResponseEntity<>(scheduleServiceList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id) {

        ScheduleResponseDto scheduleResponseDto = scheduleService.findById(id);

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        scheduleService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
