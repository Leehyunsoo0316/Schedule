package com.example.schedule.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ScheduleRequestDto {

    @Setter
    // 할일
    private String task;
    // 작성자명
    private String name;
    // 비밀번호
    private String password;
}
