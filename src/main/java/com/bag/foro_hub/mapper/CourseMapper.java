package com.bag.foro_hub.mapper;

import com.bag.foro_hub.model.dto.request.CreateCourseRequest;
import com.bag.foro_hub.model.dto.response.CourseResponse;
import com.bag.foro_hub.model.entity.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {

  Course toCourse(CreateCourseRequest request);

  CourseResponse toCourseResponse(Course course);
}
