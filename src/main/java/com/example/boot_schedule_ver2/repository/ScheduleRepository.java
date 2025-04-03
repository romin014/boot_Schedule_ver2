package com.example.boot_schedule_ver2.repository;

import com.example.boot_schedule_ver2.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
