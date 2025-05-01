package com.gym.gym_system.service;

import com.gym.gym_system.dto.SubscriptionAttendanceReportDTO;
import com.gym.gym_system.dto.SubscriptionResponseDTO;
import com.gym.gym_system.entity.GymPackage;
import com.gym.gym_system.entity.Member;
import com.gym.gym_system.entity.Subscription;
import com.gym.gym_system.repository.AttendanceRepository;
import com.gym.gym_system.repository.MemberRepository;
import com.gym.gym_system.repository.PackageRepository;
import com.gym.gym_system.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.gym_system.dto.SubscriptionDTO;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private MemberRepository memberRepository; // ✅ أضفناه

    @Autowired
    private PackageRepository packageRepository; // ✅ أضفناه

    @Autowired
    private AttendanceRepository attendanceRepository;

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public Optional<Subscription> getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id);
    }

    public Subscription createSubscription(SubscriptionDTO dto) {
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        GymPackage gymPackage = packageRepository.findById(dto.getPackageId())
                .orElseThrow(() -> new RuntimeException("Package not found"));

        Subscription subscription = new Subscription();
        subscription.setMember(member);
        subscription.setaPackage(gymPackage);
        subscription.setStartDate(dto.getStartDate());
        subscription.setEndDate(dto.getEndDate());
        subscription.setAmountPaid(dto.getAmountPaid());

        return subscriptionRepository.save(subscription);
    }

    public Subscription updateSubscription(Long id, SubscriptionDTO dto) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        GymPackage gymPackage = packageRepository.findById(dto.getPackageId())
                .orElseThrow(() -> new RuntimeException("Package not found"));

        subscription.setMember(member);
        subscription.setaPackage(gymPackage);
        subscription.setStartDate(dto.getStartDate());
        subscription.setEndDate(dto.getEndDate());
        subscription.setAmountPaid(dto.getAmountPaid());

        return subscriptionRepository.save(subscription);
    }

    public void deleteSubscription(Long id) {
        subscriptionRepository.deleteById(id);
    }






    public List<SubscriptionResponseDTO> getAllSubscriptionsFormatted() {
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        List<SubscriptionResponseDTO> responseList = new ArrayList<>();

        for (Subscription sub : subscriptions) {
            SubscriptionResponseDTO dto = new SubscriptionResponseDTO();
            dto.setId(sub.getId());
            dto.setMemberName(sub.getMember().getName());
            dto.setPackageName(sub.getaPackage().getName());
            dto.setStartDate(sub.getStartDate());
            dto.setEndDate(sub.getEndDate());
            dto.setAmountPaid(sub.getAmountPaid());

            responseList.add(dto);
        }

        return responseList;
    }



    public SubscriptionAttendanceReportDTO getSubscriptionAttendanceReport(Long subscriptionId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        Member member = subscription.getMember();

        long attendanceCount = attendanceRepository.countAttendancesWithinPeriod(
                member.getId(),
                subscription.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                subscription.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        );

        SubscriptionAttendanceReportDTO dto = new SubscriptionAttendanceReportDTO();
        dto.setMemberName(member.getName());
        dto.setStartDate(subscription.getStartDate());
        dto.setEndDate(subscription.getEndDate());
        dto.setAttendanceCount(attendanceCount);

        return dto;
    }



    public List<Subscription> getExpiredSubscriptions() {
        LocalDate today = LocalDate.now();
        return subscriptionRepository.findByEndDateBefore(Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }


    public Double getTotalRevenue() {
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        return subscriptions.stream().mapToDouble(Subscription::getAmountPaid).sum();
    }


    public List<Member> getActiveSubscriptions() {
        LocalDate today = LocalDate.now();
        return subscriptionRepository.findByStartDateBefore(Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .stream().filter(sub -> sub.getEndDate().after(Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant())))
                .map(Subscription::getMember).collect(Collectors.toList());
    }


}
