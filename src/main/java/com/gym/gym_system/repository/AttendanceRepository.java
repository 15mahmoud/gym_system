package com.gym.gym_system.repository;

import com.gym.gym_system.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @Query("SELECT COUNT(a) FROM Attendance a " +
            "WHERE a.member.id = :memberId " +
            "AND a.date BETWEEN :start AND :end")
    long countAttendancesWithinPeriod(@Param("memberId") Long memberId,
                                      @Param("start") LocalDate start,
                                      @Param("end") LocalDate end);


    boolean existsByMemberIdAndDate(Long memberId, LocalDate date);

    long countByMemberId(Long memberId);

    List<Attendance> findByMemberId(Long memberId);

}
