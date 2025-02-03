package com.example.schedule.repository;

import com.example.schedule.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    Schedule save(Schedule schedule); // 저장
    Optional<Schedule> findById(Long id); // 단건 조회
    List<Schedule> findAll(); // 다건 조회
    Schedule updateTask(Long id, String task); // 수정
    void deleteById(Long id); // 삭제
}
