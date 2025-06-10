package com.mamadou.bah.si.pmu.infrastructure.rest;

import com.mamadou.bah.si.pmu.KafkaMockConfig;
import com.mamadou.bah.si.pmu.domain.model.transaction.Partant;
import com.mamadou.bah.si.pmu.infrastructure.rest.input.CourseAddInput;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static com.mamadou.bah.si.pmu.TestUtils.asJsonString;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import(KafkaMockConfig.class)
public class CourseControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry; // mock listeners

    @Test
    public void whileAddingCourse_whenValidCourse_thenCourseShouldBeAdded() throws Exception {
        final LocalDate valueDate = LocalDate.of(2025, 5, 14);

        final List<Partant> partants = List.of(
                new Partant("HORSY DREAM", 1L),
                new Partant("HOOKER BERRY", 2L),
                new Partant("FLAMME DU GOUTIER", 3L)
        );

        final CourseAddInput input = new CourseAddInput("Prix d'Amérique", valueDate, 1L, partants);

        mockMvc.perform(post("/api/v1/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(asJsonString(input)))
                .andExpect(status().isCreated());
    }
}
