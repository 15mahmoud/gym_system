package com.gym.gym_system.controller;

import com.gym.gym_system.dto.SubscriptionAttendanceReportDTO;
import com.gym.gym_system.dto.SubscriptionDTO;
import com.gym.gym_system.dto.SubscriptionResponseDTO;
import com.gym.gym_system.entity.Member;
import com.gym.gym_system.entity.Subscription;
import com.gym.gym_system.service.SubscriptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {


    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping
    public List<Subscription> getAllSubscriptions() {
        return subscriptionService.getAllSubscriptions();
    }

    @GetMapping("/{id}")
    public Optional<Subscription> getSubscriptionById(@PathVariable Long id) {
        return subscriptionService.getSubscriptionById(id);
    }

    @PostMapping
    public Subscription createSubscription(@RequestBody SubscriptionDTO dto) {
        return subscriptionService.createSubscription(dto);
    }

    @PutMapping("/{id}")
    public Subscription updateSubscription(@PathVariable Long id, @RequestBody SubscriptionDTO dto) {
        return subscriptionService.updateSubscription(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
    }


    @GetMapping("/formatted")
    public List<SubscriptionResponseDTO> getFormattedSubscriptions() {
        return subscriptionService.getAllSubscriptionsFormatted();
    }


    @GetMapping("/{id}/attendance-report")
    public SubscriptionAttendanceReportDTO getAttendanceReport(@PathVariable Long id) {
        return subscriptionService.getSubscriptionAttendanceReport(id);
    }


    @GetMapping("/expired-subscriptions")
    public List<Subscription> getExpiredSubscriptions() {
        return subscriptionService.getExpiredSubscriptions();
    }


    @GetMapping("/total-revenue")
    public Double getTotalRevenue() {
        return subscriptionService.getTotalRevenue();
    }


    @GetMapping("/active-subscriptions")
    public List<Member> getActiveSubscriptions() {
        return subscriptionService.getActiveSubscriptions();
    }


}
