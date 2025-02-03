package com.example.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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
    private LocalDate createdAt;
    // 수정일
    private LocalDate updatedAt;

    public Schedule (String task, String name, String password) {
        this.task = task;
        this.name = name;
        this.password = password;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    public Schedule(long id, String task, String name, String password, LocalDate createdAt, LocalDate updatedAt) {
        this.id = id;
        this.task = task;
        this.name = name;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Schedule(long id, String task, String name, LocalDate createdAt, LocalDate updatedAt) {
        this.id = id;
        this.task = task;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
