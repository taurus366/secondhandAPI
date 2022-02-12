package com.secondhand.secondhand.model.entity;

import org.aspectj.lang.annotation.AfterReturning;

import javax.persistence.*;
import java.time.Instant;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Instant created;

    @Column(nullable = false)
    private Instant modified;

    public Long getId() {
        return id;
    }

    public BaseEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public BaseEntity setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public Instant getModified() {
        return modified;
    }

    public BaseEntity setModified(Instant modified) {
        this.modified = modified;
        return this;
    }

    @PrePersist
    public void beforeCreate(){
        this.created = Instant.now();
        System.out.println("PRE PERSIST(BEFORE CREATE) LOADED __________________________________________");
    }

    @PostPersist
    public void onUpdate(){
        this.modified = Instant.now();
        System.out.println("POST PERSIST(AFTER UPDATE) LOADED __________________________________________________");
    }
}
