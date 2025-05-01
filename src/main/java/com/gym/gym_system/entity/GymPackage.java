package com.gym.gym_system.entity;

import jakarta.persistence.*;
        import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "packages")
public class GymPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Double price;

    private Integer numberOfDays;

    private Boolean includesCardio;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public Boolean getIncludesCardio() {
        return includesCardio;
    }

    public void setIncludesCardio(Boolean includesCardio) {
        this.includesCardio = includesCardio;
    }
    public GymPackage(){

    }

    public GymPackage(Long id, String name, Double price, Integer numberOfDays, Boolean includesCardio) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.numberOfDays = numberOfDays;
        this.includesCardio = includesCardio;
    }
}
