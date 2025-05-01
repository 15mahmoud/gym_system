package com.gym.gym_system.dto;

import java.util.Date;

public class SubscriptionAttendanceReportDTO {
    private String memberName;
    private Date startDate;
    private Date endDate;
    private long attendanceCount;

    // Getters & Setters
    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getAttendanceCount() {
        return attendanceCount;
    }

    public void setAttendanceCount(long attendanceCount) {
        this.attendanceCount = attendanceCount;
    }
}
