package com.gym.gym_system.dto;

import java.util.Date;

public class SubscriptionDTO {
    private Long memberId;
    private Long packageId;
    private Date startDate;
    private Date endDate;
    private Double amountPaid;

    // Getters
    public Long getMemberId() {
        return memberId;
    }

    public Long getPackageId() {
        return packageId;
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
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
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
