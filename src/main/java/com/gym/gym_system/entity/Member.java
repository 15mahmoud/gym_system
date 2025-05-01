package com.gym.gym_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private Subscription subscription;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member(){

    }
    public Member(Long id, String name, String phoneNumber, Subscription subscription) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.subscription = subscription;
    }
}


