package com.mamadou.bah.si.pmu.domain.services;

import com.mamadou.bah.si.pmu.domain.exception.BusinessException;
import com.mamadou.bah.si.pmu.domain.model.transaction.Course;

public interface CourseService {

    /**
     * Adds a new course to the system
     *
     * @param course the Course object to be added
     * @return The added Course Object
     * @throws BusinessException if an error occurs during the addition process
     */
    Course addCourse(final Course course) throws BusinessException;

}
