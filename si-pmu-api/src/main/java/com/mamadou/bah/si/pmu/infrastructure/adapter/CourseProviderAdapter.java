package com.mamadou.bah.si.pmu.infrastructure.adapter;

import com.mamadou.bah.si.pmu.domain.model.transaction.Course;
import com.mamadou.bah.si.pmu.domain.repository.CourseProvider;
import com.mamadou.bah.si.pmu.infrastructure.mapper.CourseMapper;
import com.mamadou.bah.si.pmu.infrastructure.persistence.entities.CourseEntity;
import com.mamadou.bah.si.pmu.infrastructure.persistence.repositories.CourseJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Configuration
@Primary
@Repository
@RequiredArgsConstructor
public class CourseProviderAdapter implements CourseProvider {

    private final CourseJpaRepository courseJpaRepository;
    private final CourseMapper courseMapper;

    @Override
    @Transactional
    public Course addCourse(Course course) {
        CourseEntity toUpdateOrCreate = courseMapper.toCourseEntity(course);

        Optional<CourseEntity> existingOpt = courseJpaRepository.lockByNumberAndDate(toUpdateOrCreate.getNumber(), toUpdateOrCreate.getDate());

        CourseEntity saved;

        if (existingOpt.isPresent()) {
            CourseEntity existing = existingOpt.get();
            existing.setName(toUpdateOrCreate.getName());
            existing.setDate(toUpdateOrCreate.getDate());

            // Supprime les partants existants
            existing.getPartants().clear();

            // Ré-associe les nouveaux partants
            toUpdateOrCreate.getPartants().forEach(p -> {
                p.setCourse(existing);
                existing.getPartants().add(p);
            });

            saved = courseJpaRepository.save(existing);
        } else {
            toUpdateOrCreate.getPartants().forEach(p -> p.setCourse(toUpdateOrCreate));
            saved = courseJpaRepository.save(toUpdateOrCreate);
        }

        return courseMapper.toSiPmuCourse(saved);
    }

}
