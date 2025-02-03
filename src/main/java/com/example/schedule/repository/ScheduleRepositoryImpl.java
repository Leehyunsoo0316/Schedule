package com.example.schedule.repository;

import com.example.schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepositoryImpl (DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Schedule save(Schedule schedule) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into schedule(task, name, password, created_at, updated_at) values(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, schedule.getTask());
            ps.setString(2, schedule.getName());
            ps.setString(3, schedule.getPassword());
            ps.setDate(4, Date.valueOf(schedule.getCreatedAt()));
            ps.setDate(5, Date.valueOf(schedule.getUpdatedAt()));
            return ps;
        }, keyHolder);

        schedule.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        return schedule;
    }

    @Override
    public Optional<Schedule> findById(Long id) {
        List<Schedule> schedules = jdbcTemplate.query(
                "select * from schedule where id = ?",
                (rs, rowNum) -> new Schedule(
                        rs.getLong("id"),
                        rs.getString("task"),
                        rs.getString("name"),
                        rs.getDate("created_at").toLocalDate(),
                        rs.getDate("updated_at").toLocalDate()
                ),
                id
        );

        return schedules.stream().findAny();
    }

    @Override
    public List<Schedule> findAll() {
        return jdbcTemplate.query(
                "select * from schedule",
                (rs, rowNum) ->
                        new Schedule(
                                rs.getLong("id"),
                                rs.getString("task"),
                                rs.getString("name"),
                                rs.getDate("created_at").toLocalDate(),
                                rs.getDate("updated_at").toLocalDate()
                        )
        );
    }

    @Override
    public Schedule update(Long id, String task, String name, String password) {
        // 비밀번호 확인
        Schedule existingSchedule = jdbcTemplate.queryForObject(
                "select * from schedule where id = ?",
                (rs, rowNum) -> new Schedule(
                        rs.getLong("id"),
                        rs.getString("task"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getDate("created_at").toLocalDate(),
                        rs.getDate("updated_at").toLocalDate()
                ),
                id
        );

        if (existingSchedule == null) {
            throw new IllegalArgumentException("ID가 " + id + "인 일정이 존재하지 않습니다.");
        }

        // 비밀번호가 일치하지 않으면 예외 처리
        if (!existingSchedule.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 비밀번호가 일치하면 수정
        LocalDate updatedAt = LocalDate.now();

        int updatedRows = jdbcTemplate.update(
                "update schedule set task = ?, name = ?, updated_at = ? where id = ?",
                task,
                name,
                Date.valueOf(updatedAt),
                id
        );

        if (updatedRows == 0) {
            throw new IllegalArgumentException("ID가 " + id + "인 일정이 존재하지 않습니다.");
        }

        return jdbcTemplate.queryForObject(
                "select * from schedule where id = ?",
                (rs, rowNum) ->
                        new Schedule(
                                rs.getLong("id"),
                                rs.getString("task"),
                                rs.getString("name"),
                                rs.getString("password"),
                                rs.getDate("created_at").toLocalDate(),
                                rs.getDate("updated_at").toLocalDate()
                        ),
                id
        );
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(
                "DELETE FROM schedule WHERE id = ?",
                id
        );
    }
}
