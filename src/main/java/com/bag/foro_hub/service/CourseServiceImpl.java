package com.bag.foro_hub.service;

import com.bag.foro_hub.exceptions.CourseNotFoundException;
import com.bag.foro_hub.mapper.CourseMapper;
import com.bag.foro_hub.model.dto.request.CreateCourseRequest;
import com.bag.foro_hub.model.dto.response.CourseResponse;
import com.bag.foro_hub.model.entity.Course;
import com.bag.foro_hub.repository.CourseRepository;
import com.bag.foro_hub.utils.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

  private final CourseRepository courseRepository;
  private final CourseMapper courseMapper;

  @Override
  @Transactional
  public CourseResponse save(CreateCourseRequest request) {
    Course course = courseMapper.toCourse(request);
    return courseMapper.toCourseResponse(courseRepository.save(course));
  }

  @Override
  @Transactional(readOnly = true)
  public List<CourseResponse> findAll() {
    return courseRepository.findAll().stream().map(courseMapper::toCourseResponse).toList();
  }

  @Override
  @Transactional(readOnly = true)
  public CourseResponse findById(Long id) {
    return courseRepository
        .findById(id)
        .map(courseMapper::toCourseResponse)
        .orElseThrow(CourseNotFoundException::new);
  }

  @Override
  @Transactional
  public CourseResponse update(Long id, CreateCourseRequest request) {
    return courseRepository
        .findById(id)
        .map(
            course -> {
              course.setName(request.name());
              course.setCategory(Category.valueOf(request.category()));
              return courseRepository.save(course);
            })
        .map(courseMapper::toCourseResponse)
        .orElseThrow(CourseNotFoundException::new);
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    if (!courseRepository.existsById(id)) {
      throw new CourseNotFoundException();
    }
    courseRepository.deleteById(id);
  }
}
