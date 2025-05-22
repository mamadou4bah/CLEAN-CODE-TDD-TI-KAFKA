package com.mamadou.bah.si.pmu.domain.repository;

import com.mamadou.bah.si.pmu.domain.model.transaction.Course;

public interface CourseEventPublishProvider {

    void publishCourseCreated(Course course);
}
