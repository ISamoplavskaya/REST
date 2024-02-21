package com.example.rest;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicLong;


@Data
@NoArgsConstructor
public class User {
    private static final AtomicLong idCounter = new AtomicLong();
    private Long id;
    private String userName;
    private int age;

    public User(String userName, int age) {
        this.id = idCounter.incrementAndGet();
        this.userName = userName;
        this.age = age;
    }

    public User(Long id, String userName, int age) {
        this.id = id;
        this.userName = userName;
        this.age = age;
    }
}
