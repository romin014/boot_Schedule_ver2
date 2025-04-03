package com.example.boot_schedule_ver2.repository;

import com.example.boot_schedule_ver2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
