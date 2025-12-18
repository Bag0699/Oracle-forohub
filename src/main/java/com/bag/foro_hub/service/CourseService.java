package com.bag.foro_hub.service;

import com.bag.foro_hub.model.dto.request.CreateCourseRequest;
import com.bag.foro_hub.model.dto.response.CourseResponse;

import java.util.List;

public interface CourseService {

  CourseResponse save(CreateCourseRequest request);

  List<CourseResponse> findAll();

  CourseResponse findById(Long id);

  CourseResponse update(Long id, CreateCourseRequest request);

  void deleteById(Long id);
}
