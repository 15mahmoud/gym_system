package com.gym.gym_system.repository;

import com.gym.gym_system.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    List<Subscription> findByEndDateBefore(Date endDate);
    List<Subscription> findByStartDateBefore(Date startDate);
}
