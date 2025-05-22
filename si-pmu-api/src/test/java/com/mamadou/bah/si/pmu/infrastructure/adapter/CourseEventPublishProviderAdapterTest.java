package com.mamadou.bah.si.pmu.infrastructure.adapter;

import com.mamadou.bah.si.pmu.KafkaMockConfigCoursePublishEvent;
import com.mamadou.bah.si.pmu.domain.model.transaction.Course;
import com.mamadou.bah.si.pmu.domain.model.transaction.Partant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
@Import(KafkaMockConfigCoursePublishEvent.class)
public class CourseEventPublishProviderAdapterTest {

    @MockBean
    private KafkaTemplate<String, Course> kafkaTemplate;

    @MockBean
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry; // mock listeners

    @Autowired
    private CourseEventPublishProviderAdapter adapter;

    @Test
    void shouldSendCourseEventToKafka() {

        Course course = new Course("Prix d'Amérique", LocalDate.now(), 1L, List.of(
                new Partant("HORSY DREAM", 1L),
                new Partant("HOOKER BERRY", 2L),
                new Partant("FLAMME DU GOUTIER", 3L)
        ));
        // Appel de la méthode à tester
        adapter.publishCourseCreated(course);

        verify(kafkaTemplate).send(eq("course-publish"), eq("1"), any(Course.class));
    }
}
