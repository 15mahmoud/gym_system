package com.gym.gym_system.service;

import com.gym.gym_system.dto.AttendanceDTO;
import com.gym.gym_system.entity.Attendance;
import com.gym.gym_system.entity.Member;
import com.gym.gym_system.entity.Subscription;
import com.gym.gym_system.repository.AttendanceRepository;
import com.gym.gym_system.repository.MemberRepository;
import com.gym.gym_system.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private MemberRepository memberRepository;


    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public List<Attendance> getAllAttendances() {
        return attendanceRepository.findAll();
    }

    public Optional<Attendance> getAttendanceById(Long id) {
        return attendanceRepository.findById(id);
    }

    public Attendance createAttendance(AttendanceDTO dto) {
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Attendance attendance = new Attendance();
        attendance.setMember(member);
        attendance.setDate(dto.getDate());
        attendance.setTime(dto.getTime());

        return attendanceRepository.save(attendance);
    }

    public Attendance updateAttendance(Long id, AttendanceDTO dto) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        attendance.setMember(member);
        attendance.setDate(dto.getDate());
        attendance.setTime(dto.getTime());

        return attendanceRepository.save(attendance);
    }

    public void deleteAttendance(Long id) {
        attendanceRepository.deleteById(id);
    }



    public String checkIn(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Subscription subscription = subscriptionRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("No subscription found for this member"));

        LocalDate today = LocalDate.now();
        LocalDate start = subscription.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = subscription.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (today.isBefore(start) || today.isAfter(end)) {
            throw new RuntimeException("Subscription not valid for today");
        }

        boolean alreadyCheckedIn = attendanceRepository.existsByMemberIdAndDate(memberId, today);
        if (alreadyCheckedIn) {
            throw new RuntimeException("Already checked in today");
        }

        Attendance attendance = new Attendance();
        attendance.setMember(member);
        attendance.setDate(today);
        attendance.setTime(LocalTime.now());

        attendanceRepository.save(attendance);

        return "Check-in successful!";
    }


    public long getAttendanceCount(Long memberId) {
        return attendanceRepository.countByMemberId(memberId);
    }


    public List<Attendance> getMemberAttendance(Long memberId) {
        return attendanceRepository.findByMemberId(memberId);
    }

}
