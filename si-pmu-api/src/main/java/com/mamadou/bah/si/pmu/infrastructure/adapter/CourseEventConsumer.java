package com.mamadou.bah.si.pmu.infrastructure.adapter;

import com.mamadou.bah.si.pmu.domain.model.transaction.Course;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CourseEventConsumer {

    @KafkaListener(topics = "course-publish", groupId = "course-consumer-group", containerFactory = "courseKafkaListenerContainerFactory")
    public void listenCourseEvents(ConsumerRecord<String, Course> record) {
        Course course = record.value();
        System.out.println("✅ Course received: " + course.name() + " | #" + course.number());
        // ...traitement du cours ici
    }
}
