package com.example.boot_schedule_ver2.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String todoTitle;

    @Column(columnDefinition = "longtext")
    private String todoContents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Schedule(){

    }

    public Schedule(String userName, String todoTitle, String todoContents) {
        this.userName = userName;
        this.todoTitle = todoTitle;
        this.todoContents = todoContents;
    }

    public void update(String todoTitle, String todoContents) {
        this.todoTitle = todoTitle;
        this.todoContents = todoContents;
    }

}
