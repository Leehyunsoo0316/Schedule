package com.example.schedule.controller;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<ScheduleResponseDto> saveSchedule(@RequestBody ScheduleRequestDto dto) {
        return ResponseEntity.ok(scheduleService.saveSchedule(dto));
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleResponseDto>> getSchedule () {
        return ResponseEntity.ok(scheduleService.getSchedule());
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> getSchedule (@PathVariable Long scheduleId) {
        return ResponseEntity.ok(scheduleService.getSchedule(scheduleId));
    }

    @PutMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule (@PathVariable Long scheduleId, @RequestBody ScheduleRequestDto dto) {
        return ResponseEntity.ok(scheduleService.updateSchedule(scheduleId, dto));
    }

    @DeleteMapping("/schedules/{scheduleId}")
    public void deleteSchedule (@PathVariable Long scheduleId) {
        scheduleService.deleteScheduleById(scheduleId);
    }
}
