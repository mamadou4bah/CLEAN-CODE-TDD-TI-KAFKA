package com.mamadou.bah.si.pmu.infrastructure.rest;

import com.mamadou.bah.si.pmu.domain.model.transaction.Course;
import com.mamadou.bah.si.pmu.domain.services.CourseService;
import com.mamadou.bah.si.pmu.infrastructure.mapper.CourseMapper;
import com.mamadou.bah.si.pmu.infrastructure.rest.input.CourseAddInput;
import com.mamadou.bah.si.pmu.infrastructure.rest.output.CourseOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@Log4j2
@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class CourseController {

    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @PostMapping
    @Operation(description = "Allow to add course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The course has been added"),
    })
    public ResponseEntity<CourseOutput> addCourse(@RequestBody @Valid CourseAddInput courseAddInput) {
        logger.debug("Adding a new course start");
        final Course course = courseService.addCourse(courseMapper.toAddCourse(courseAddInput));
        logger.debug("Adding a new course end");
        return new ResponseEntity<>(courseMapper.toCourseOutPut(course), CREATED);
    }
}
