package com.example.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {

    // 고유 식별자
    private Long id;
    // 할일
    private String task;
    // 작성자명
    private String name;
    // 작성일
    private LocalDateTime createdAt;
    // 수정일
    private LocalDateTime updatedAt;

    public ScheduleResponseDto (Long id, String task, String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.task = task;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}