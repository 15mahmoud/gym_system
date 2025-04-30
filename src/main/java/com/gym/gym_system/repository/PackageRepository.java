package com.gym.gym_system.repository;

import com.gym.gym_system.entity.GymPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<GymPackage,Long> {
}



