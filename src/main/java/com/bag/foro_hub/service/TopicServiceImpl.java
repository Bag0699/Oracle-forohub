package com.bag.foro_hub.service;

import com.bag.foro_hub.exceptions.CourseNotFoundException;
import com.bag.foro_hub.exceptions.RoleAccessDeniedException;
import com.bag.foro_hub.exceptions.TopicNotFoundException;
import com.bag.foro_hub.exceptions.UserNotFoundException;
import com.bag.foro_hub.mapper.TopicMapper;
import com.bag.foro_hub.model.dto.request.CreateTopicRequest;
import com.bag.foro_hub.model.dto.request.UpdateTopicRequest;
import com.bag.foro_hub.model.dto.response.TopicResponse;
import com.bag.foro_hub.model.entity.Course;
import com.bag.foro_hub.model.entity.Topic;
import com.bag.foro_hub.model.entity.User;
import com.bag.foro_hub.repository.CourseRepository;
import com.bag.foro_hub.repository.TopicRepository;
import com.bag.foro_hub.repository.UserRepository;
import com.bag.foro_hub.validation.TopicValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

  private final TopicRepository topicRepository;
  private final CourseRepository courseRepository;
  private final UserRepository userRepository;
  private final TopicValidator topicValidator;
  private final TopicMapper topicMapper;

  @Override
  @Transactional
  public TopicResponse save(CreateTopicRequest request, Long authId) {

    topicValidator.validate(request);
    User user = userRepository.findById(authId).orElseThrow(UserNotFoundException::new);
    Course course =
        courseRepository.findById(request.courseId()).orElseThrow(CourseNotFoundException::new);

    Topic topic = new Topic();
    topic.setTitle(request.title());
    topic.setMessage(request.message());
    topic.setUser(user);
    topic.setCourse(course);
    return topicMapper.toTopicResponse(topicRepository.save(topic));
  }

  @Override
  @Transactional(readOnly = true)
  public List<TopicResponse> findAll() {
    return topicRepository.findAll().stream().map(topicMapper::toTopicResponse).toList();
  }

  @Override
  @Transactional(readOnly = true)
  public TopicResponse findById(Long id) {
    return topicRepository
        .findById(id)
        .map(topicMapper::toTopicResponse)
        .orElseThrow(TopicNotFoundException::new);
  }

  @Override
  @Transactional
  public TopicResponse update(Long id, UpdateTopicRequest request, Long authId) {
    Topic topic = topicRepository.findById(id).orElseThrow(TopicNotFoundException::new);

    boolean isOwner = topic.belongsToUser(authId);
    if (!isOwner) {
      throw new RoleAccessDeniedException("You are not the owner of this topic");
    }

    topicValidator.valideteForUpdate(request, id);

    Course course =
        courseRepository.findById(request.courseId()).orElseThrow(CourseNotFoundException::new);

    topic.setTitle(request.title());
    topic.setMessage(request.message());
    topic.setCourse(course);

    return topicMapper.toTopicResponse(topicRepository.save(topic));
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    if (!topicRepository.existsById(id)) {
      throw new TopicNotFoundException();
    }
    topicRepository.deleteById(id);
  }
}
