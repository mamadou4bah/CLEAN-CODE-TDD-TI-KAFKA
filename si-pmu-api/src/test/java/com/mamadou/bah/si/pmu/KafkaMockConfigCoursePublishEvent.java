package com.mamadou.bah.si.pmu;

import com.mamadou.bah.si.pmu.domain.model.transaction.Course;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;

@TestConfiguration
public class KafkaMockConfigCoursePublishEvent {

    @Bean
    @Primary
    public KafkaTemplate<String, Course> mockKafkaTemplate() {
        System.out.println("Création du mock KafkaTemplate");
        return Mockito.mock(KafkaTemplate.class);
    }
}
