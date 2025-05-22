package com.mamadou.bah.si.pmu.application;

import com.mamadou.bah.si.pmu.domain.common.DomainService;
import com.mamadou.bah.si.pmu.domain.exception.BusinessException;
import com.mamadou.bah.si.pmu.domain.model.transaction.Course;
import com.mamadou.bah.si.pmu.domain.model.transaction.Partant;
import com.mamadou.bah.si.pmu.domain.repository.CourseEventPublishProvider;
import com.mamadou.bah.si.pmu.domain.repository.CourseProvider;
import com.mamadou.bah.si.pmu.domain.services.CourseService;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@DomainService
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseProvider courseProvider;
    private final CourseEventPublishProvider courseEventPublishProvider;

    @Override
    public Course addCourse(Course course) throws BusinessException {
        validateCourse(course);
        courseEventPublishProvider.publishCourseCreated(course);
        return courseProvider.addCourse(course);
    }

    /**
     * Valide les contraintes métier d'une course.
     */
    private void validateCourse(Course course) throws BusinessException {
        List<Partant> partants = course.partants(); // Accède à la liste des partants

        if (partants == null || partants.size() < 3) {
            throw new BusinessException("COURSE_MIN_PARTANTS", "Une course doit contenir au moins 3 partants.");
        }

        Set<Long> number = partants.stream()
                .map(Partant::number) // Extrait les numéros des partants
                .collect(Collectors.toSet());

        // Vérifie que les numéros sont uniques
        if (number.size() != partants.size()) {
            throw new BusinessException("COURSE_UNIQUE_NUMBER", "Les numéros des partants doivent être uniques.");
        }

        // Vérifie que les numéros des partants commencent à 1 et sont consécutifs
        long maxNumber = Collections.max(number); // Numéro maximum des partants
        if (maxNumber != partants.size()) {
            throw new BusinessException("COURSE_CONSECUTIVE_NUMBER", "Les numéros des partants doivent commencer à 1 et être consécutifs.");
        }

        // Vérifie que tous les numéros de 1 à maxNumber sont présents
        for (long i = 1; i <= maxNumber; i++) {
            if (!number.contains(i)) {
                throw new BusinessException("COURSE_CONSECUTIVE_NUMBER", "Les numéros des partants doivent commencer à 1 et être consécutifs.");
            }
        }
    }


}
