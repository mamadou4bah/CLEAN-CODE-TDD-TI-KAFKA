package com.mamadou.bah.si.pmu.infrastructure.adapter;

import com.mamadou.bah.si.pmu.domain.model.transaction.Course;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CourseEventConsumer {

    private static final Logger logger = LoggerFactory.getLogger(CourseEventConsumer.class);

    @KafkaListener(topics = "course-publish", groupId = "course-consumer-group", containerFactory = "courseKafkaListenerContainerFactory")
    public void listenCourseEvents(ConsumerRecord<String, Course> record) {
        Course course = record.value();
        logger.info("✅ Course received: {} | # {}" , course.name(), course.number());
        // ...traitement du cours ici
    }
}
