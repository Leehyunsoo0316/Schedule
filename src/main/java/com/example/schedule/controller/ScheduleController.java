package com.example.schedule.controller;


import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final Map<Long, Schedule> scheduleMap = new HashMap<>();

    // 일정 생성
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule (@RequestBody ScheduleRequestDto dto) {
        // 식별자가 1씩 증가 하도록
        Long scheduleId = scheduleMap.isEmpty() ? 1 : Collections.max(scheduleMap.keySet()) + 1;

        // 요청받은 데이터로 Schedule 객체 생성
        Schedule schedule = new Schedule(scheduleId, dto.getTask(), dto.getName(), dto.getPassword());

        // Inmemory DB에 Schedule 등록
        scheduleMap.put(scheduleId, schedule);

        return new ResponseEntity<>(new ScheduleResponseDto(schedule), HttpStatus.CREATED);
    }

    // 일정 목록 조회
    @GetMapping
    public List<ScheduleResponseDto> findAllSchedule () {
        // init List
        List<ScheduleResponseDto> responseList = new ArrayList<>();

        // HashMap<Schedule> -> List<ScheduleResponseDto>
        for (Schedule schedule : scheduleMap.values()) {
            ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);
            responseList.add(responseDto);
        }

        return responseList;
    }

    // 일정 단건 조회
    @GetMapping("/{id}")
    public ScheduleResponseDto findScheduleById (@PathVariable Long id) {
        Schedule schedule = scheduleMap.get(id);

        return new ScheduleResponseDto(schedule);
    }
}
