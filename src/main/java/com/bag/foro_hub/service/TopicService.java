package com.bag.foro_hub.service;

import com.bag.foro_hub.model.dto.request.CreateTopicRequest;
import com.bag.foro_hub.model.dto.request.UpdateTopicRequest;
import com.bag.foro_hub.model.dto.response.TopicResponse;
import org.springframework.data.domain.Page;

public interface TopicService {

  TopicResponse save(CreateTopicRequest request, Long authId);

  Page<TopicResponse> findAll(int page, int size);

  Page<TopicResponse> findAllByCourseAndYear(Long courseId, int year, int page, int size);

  TopicResponse findById(Long id);

  TopicResponse update(Long id, UpdateTopicRequest request, Long authId);

  void deleteById(Long id, Long authId);
}
