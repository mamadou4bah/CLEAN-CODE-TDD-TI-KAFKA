package com.mamadou.bah.si.pmu.infrastructure.persistence.repositories;

import com.mamadou.bah.si.pmu.infrastructure.persistence.entities.CourseEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface CourseJpaRepository extends JpaRepository<CourseEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM CourseEntity c WHERE c.number = :number AND c.date = :date")
    Optional<CourseEntity> lockByNumberAndDate(@Param("number") Long number, @Param("date") LocalDate date);


}
