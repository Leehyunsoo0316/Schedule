package com.example.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {

    @Setter
    // 고유 식별자
    private Long id;
    // 할일
    private String task;
    // 작성자명
    private String name;
    // 비밀번호
    private String password;
    // 작성일
    private LocalDateTime createdAt;
    // 수정일
    private LocalDateTime updatedAt;

    public Schedule (String task, String name, String password, LocalDateTime createdAt) {
        this.task = task;
        this.name = name;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }
}
