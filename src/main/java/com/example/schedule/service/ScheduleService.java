package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponseDto saveSchedule (ScheduleRequestDto dto) {
        Schedule schedule = new Schedule(dto.getTask(), dto.getName(), dto.getPassword());
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(
                savedSchedule.getId(),
                savedSchedule.getTask(),
                savedSchedule.getName(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getUpdatedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> getSchedule() {
        List<Schedule> scheduleList = scheduleRepository.findAll();

        List<ScheduleResponseDto> dtoList = new ArrayList<>();
        for (Schedule schedule : scheduleList) {
            ScheduleResponseDto responseDto = new ScheduleResponseDto(
                    schedule.getId(),
                    schedule.getTask(),
                    schedule.getName(),
                    schedule.getCreatedAt(),
                    schedule.getUpdatedAt()
            );
            dtoList.add(responseDto);
        }
        return dtoList;
    }

    @Transactional(readOnly = true)
    public ScheduleResponseDto getSchedule (Long id) {
         Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                 () -> new IllegalArgumentException("해당 일정이 존재하지 않습니다.")
         );

        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getTask(),
                schedule.getName(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long scheduleId, ScheduleRequestDto dto) {
        Schedule schedule = scheduleRepository.update(scheduleId, dto.getTask(), dto.getName(), dto.getPassword());

        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getTask(),
                schedule.getName(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }

    @Transactional
    public void deleteScheduleById (Long scheduleID, String password) {
        scheduleRepository.deleteById(scheduleID, password);
    }
}
