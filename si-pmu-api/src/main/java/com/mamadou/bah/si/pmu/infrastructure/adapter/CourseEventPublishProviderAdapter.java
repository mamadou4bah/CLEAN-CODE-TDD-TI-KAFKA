package com.mamadou.bah.si.pmu.infrastructure.adapter;

import com.mamadou.bah.si.pmu.domain.model.transaction.Course;
import com.mamadou.bah.si.pmu.domain.model.transaction.Partant;
import com.mamadou.bah.si.pmu.domain.repository.CourseEventPublishProvider;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Configuration
@Primary
@Repository
@RequiredArgsConstructor
public class CourseEventPublishProviderAdapter implements CourseEventPublishProvider {

    private final KafkaTemplate<String, Course> kafkaTemplate;

    @Override
    public void publishCourseCreated(Course course) {
        List<Partant> partants = course.partants().stream()
                .map(p -> new Partant(p.name(), p.number()))
                .toList();

        Course courseEvent = new Course(
                course.name(),
                course.date(),
                course.number(),
                partants
        );
        kafkaTemplate.send("course-publish", course.number().toString(), courseEvent);
    }
}
