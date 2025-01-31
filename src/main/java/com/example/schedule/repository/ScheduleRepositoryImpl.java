package com.example.schedule.repository;

import com.example.schedule.entity.Schedule;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final Map<Long, Schedule> scheduleMap = new HashMap<>();

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        Long scheduleId = scheduleMap.isEmpty() ? 1 : Collections.max(scheduleMap.keySet()) + 1;
        schedule.setId(scheduleId);

        scheduleMap.put(scheduleId, schedule);

        return schedule;
    }
}
