package com.example.ioc.repository;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    public void sayHi() {
        System.out.println("hi, UserRepository...");
    }
}
