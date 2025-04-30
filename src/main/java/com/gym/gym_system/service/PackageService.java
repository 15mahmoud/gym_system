package com.gym.gym_system.service;

import com.gym.gym_system.entity.GymPackage;
import com.gym.gym_system.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackageService {

    @Autowired
    private PackageRepository packageRepository;

    public List<GymPackage> getAllPackages() {
        return packageRepository.findAll();
    }

    public Optional<GymPackage> getPackageById(Long id) {
        return packageRepository.findById(id);
    }

    public GymPackage createPackage(GymPackage pack) {
        return packageRepository.save(pack);
    }

    public GymPackage updatePackage(Long id, GymPackage updatedPack) {
        updatedPack.setId(id);
        return packageRepository.save(updatedPack);
    }

    public void deletePackage(Long id) {
        packageRepository.deleteById(id);
    }
}
