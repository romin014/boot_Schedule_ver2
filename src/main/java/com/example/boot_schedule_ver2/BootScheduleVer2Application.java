package com.example.boot_schedule_ver2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BootScheduleVer2Application {

    public static void main(String[] args) {
        SpringApplication.run(BootScheduleVer2Application.class, args);
    }

}
