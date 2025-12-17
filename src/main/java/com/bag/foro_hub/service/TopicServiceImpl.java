package com.bag.foro_hub.service;

import com.bag.foro_hub.exceptions.CourseNotFoundException;
import com.bag.foro_hub.exceptions.UserNotFoundException;
import com.bag.foro_hub.mapper.TopicMapper;
import com.bag.foro_hub.model.dto.request.CreateTopicRequest;
import com.bag.foro_hub.model.dto.response.TopicResponse;
import com.bag.foro_hub.model.entity.Topic;
import com.bag.foro_hub.repository.CourseRepository;
import com.bag.foro_hub.repository.TopicRepository;
import com.bag.foro_hub.repository.UserRepository;
import com.bag.foro_hub.validation.TopicValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopiService {

  private final TopicRepository topicRepository;
  private final CourseRepository courseRepository;
  private final UserRepository userRepository;
  private final TopicValidator topicValidator;
  private final TopicMapper topicMapper;

  @Override
  public TopicResponse save(CreateTopicRequest request) {
    return userRepository
        .findById(request.userId())
        .map(
            user ->
                courseRepository
                    .findById(request.courseId())
                    .map(
                        course -> {
                          topicValidator.validate(request);
                          Topic topic = new Topic();
                          topic.setTitle(request.title());
                          topic.setMessage(request.message());
                          topic.setUser(user);
                          topic.setCourse(course);
                          return topicRepository.save(topic);
                        })
                    .map(topicMapper::toTopicResponse)
                    .orElseThrow(CourseNotFoundException::new))
        .orElseThrow(UserNotFoundException::new);
  }

  @Override
  public List<TopicResponse> findAll() {
    return topicRepository.findAll().stream().map(topicMapper::toTopicResponse).toList();
  }
}
