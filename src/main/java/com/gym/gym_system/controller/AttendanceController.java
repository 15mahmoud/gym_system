package com.gym.gym_system.controller;

import com.gym.gym_system.dto.AttendanceDTO;
import com.gym.gym_system.entity.Attendance;
import com.gym.gym_system.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/attendances")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping
    public List<Attendance> getAllAttendances() {
        return attendanceService.getAllAttendances();
    }

    @GetMapping("/{id}")
    public Optional<Attendance> getAttendanceById(@PathVariable Long id) {
        return attendanceService.getAttendanceById(id);
    }

    @PostMapping
    public Attendance createAttendance(@RequestBody AttendanceDTO dto) {
        return attendanceService.createAttendance(dto);
    }

    @PutMapping("/{id}")
    public Attendance updateAttendance(@PathVariable Long id, @RequestBody AttendanceDTO dto) {
        return attendanceService.updateAttendance(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
    }

    @PostMapping("/check-in")
    public String checkIn(@RequestParam Long memberId) {
        return attendanceService.checkIn(memberId);
    }


    @GetMapping("/attendance/{memberId}")
    public long getAttendanceCount(@PathVariable Long memberId) {
        return attendanceService.getAttendanceCount(memberId);
    }


    @GetMapping("/attendance-details/{memberId}")
    public List<Attendance> getMemberAttendance(@PathVariable Long memberId) {
        return attendanceService.getMemberAttendance(memberId);
    }


}
