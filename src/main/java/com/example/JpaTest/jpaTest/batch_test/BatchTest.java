package com.example.JpaTest.jpaTest.batch_test;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class BatchTest extends BaseEntity {

    private int age;

    private String name;

    public BatchTest(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
}