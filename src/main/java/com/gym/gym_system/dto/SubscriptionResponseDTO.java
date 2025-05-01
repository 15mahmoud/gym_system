package com.gym.gym_system.dto;

import java.util.Date;

public class SubscriptionResponseDTO {
    private Long id;
    private String memberName;
    private String packageName;
    private Date startDate;
    private Date endDate;
    private Double amountPaid;

    // Getters
    public Long getId() {
        return id;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getPackageName() {
        return packageName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }
}
