package com.mamadou.bah.si.pmu.infrastructure.mapper;

import com.mamadou.bah.si.pmu.domain.model.transaction.Course;
import com.mamadou.bah.si.pmu.infrastructure.persistence.entities.CourseEntity;
import com.mamadou.bah.si.pmu.infrastructure.rest.input.CourseAddInput;
import com.mamadou.bah.si.pmu.infrastructure.rest.output.CourseOutput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CourseMapper {

    CourseEntity toCourseEntity(final Course course);

    Course toSiPmuCourse(CourseEntity courseEntity);

    Course toAddCourse(CourseAddInput courseAddInput);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "number", source = "number")
    CourseOutput toCourseOutPut(Course course);
}
