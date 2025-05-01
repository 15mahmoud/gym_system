package com.gym.gym_system.controller;

import com.gym.gym_system.entity.GymPackage;
import com.gym.gym_system.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/packages")
public class PackageController {


    private final PackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @GetMapping
    public List<GymPackage> getAllPackages() {
        return packageService.getAllPackages();
    }

    @GetMapping("/{id}")
    public Optional<GymPackage> getPackageById(@PathVariable Long id) {
        return packageService.getPackageById(id);
    }

    @PostMapping
    public GymPackage createPackage(@RequestBody GymPackage gymPackage) {
        return packageService.createPackage(gymPackage);
    }

//    @PutMapping("/{id}")
//    public GymPackage updatePackage(@PathVariable Long id, @RequestBody GymPackage updatedPackage) {
//        return packageService.updatePackage(id, updatedPackage);
//    }

    @DeleteMapping("/{id}")
    public void deletePackage(@PathVariable Long id) {
        packageService.deletePackage(id);
    }
}
