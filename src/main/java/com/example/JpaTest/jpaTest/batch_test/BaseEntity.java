package com.example.JpaTest.jpaTest.batch_test;

import jakarta.persistence.*;
import org.springframework.data.domain.Persistable;

@MappedSuperclass
public abstract class BaseEntity implements Persistable<Long> {

    @Id
    private Long id = SnowflakeIdGenerator.getInstance().nextId();

    @Transient
    private boolean isNew = true;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    @PostPersist
    @PostLoad
    void markNotNew() {
        this.isNew = false;
    }
}
