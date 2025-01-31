package com.example.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleRequestDto {

    // 할일
    private String task;
    // 작성자명
    private String name;
    // 비밀번호
    private String password;
    // 작성시간
    private LocalDateTime createdAt;
}
