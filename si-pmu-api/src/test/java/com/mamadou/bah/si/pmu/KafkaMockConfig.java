package com.mamadou.bah.si.pmu;

import com.mamadou.bah.si.pmu.domain.model.transaction.Course;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.CompletableFuture;

@TestConfiguration
public class KafkaMockConfig {

    @Bean
    @Primary
    public KafkaTemplate<String, Course> mockKafkaTemplate() {
        KafkaTemplate<String, Course> mock = Mockito.mock(KafkaTemplate.class);
        Mockito.when(mock.send(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(CompletableFuture.completedFuture(null));
        return mock;
    }
}
