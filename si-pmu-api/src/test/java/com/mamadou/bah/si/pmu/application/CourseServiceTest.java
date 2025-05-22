package com.mamadou.bah.si.pmu.application;

import com.mamadou.bah.si.pmu.domain.exception.BusinessException;
import com.mamadou.bah.si.pmu.domain.model.transaction.Course;
import com.mamadou.bah.si.pmu.domain.model.transaction.Partant;
import com.mamadou.bah.si.pmu.domain.repository.CourseEventPublishProvider;
import com.mamadou.bah.si.pmu.domain.repository.CourseProvider;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.*; // Pour assertThrows, assertEquals, etc.
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    @Mock
    private CourseProvider courseProvider;

    @Mock
    private CourseEventPublishProvider courseEventPublishProvider;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    void addCourse_whenValidCourse_shouldCallProviderAndReturnCourse() throws BusinessException {
        // GIVEN
        final List<Partant> partants = List.of(
                new Partant("HORSY DREAM", 1L),
                new Partant("HOOKER BERRY", 2L),
                new Partant("FLAMME DU GOUTIER", 3L)
        );
        final Course course = new Course("Prix d'Amérique", LocalDate.now(), 1L, partants);

        // Mock du retour du provider
        when(courseProvider.addCourse(course)).thenReturn(course);

        // WHEN
        final Course result = courseService.addCourse(course);

        // THEN
        assertNotNull(result);
        assertEquals(course.name(), result.name());
        verify(courseProvider).addCourse(course);

        verify(courseEventPublishProvider).publishCourseCreated(course);
    }

    @Test
    void addCourse_whenLessThan3Partants_shouldThrowBusinessException() {
        // GIVEN
        final List<Partant> partants = List.of(
                new Partant("HORSY DREAM", 1L),
                new Partant("HOOKER BERRY", 2L)
        );
        final Course course = new Course("Prix d'Amérique", LocalDate.now(), 2L, partants);

        // WHEN + THEN
        BusinessException ex = assertThrows(BusinessException.class, () -> courseService.addCourse(course));
        assertEquals("COURSE_MIN_PARTANTS", ex.getCode());
    }

    @Test
    void addCourse_whenDuplicatePartantNumbers_shouldThrowBusinessException() {
        // GIVEN
        final List<Partant> partants = List.of(
                new Partant("HORSY DREAM", 1L),
                new Partant("HOOKER BERRY", 2L),
                new Partant("FLAMME DU GOUTIER", 1L) // Duplicate number
        );
        final Course course = new Course("Course doublon", LocalDate.now(), 3L, partants);

        // WHEN + THEN
        BusinessException ex = assertThrows(BusinessException.class, () -> courseService.addCourse(course));
        assertEquals("COURSE_UNIQUE_NUMBER", ex.getCode());
    }

    @Test
    void addCourse_whenPartantNumbersNotConsecutive_shouldThrowBusinessException() {
        // GIVEN
        final List<Partant> partants = List.of(
                new Partant("HORSY DREAM", 1L),
                new Partant("HOOKER BERRY", 2L),
                new Partant("FLAMME DU GOUTIER", 4L) // Trou: 3 manque
        );
        final Course course = new Course("Course trou", LocalDate.now(), 4L, partants);

        // WHEN + THEN
        BusinessException ex = assertThrows(BusinessException.class, () -> courseService.addCourse(course));
        assertEquals("COURSE_CONSECUTIVE_NUMBER", ex.getCode());
    }
}
