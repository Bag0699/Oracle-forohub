package com.bag.foro_hub.service;

import com.bag.foro_hub.model.dto.request.CreateTopicRequest;
import com.bag.foro_hub.model.dto.request.UpdateTopicRequest;
import com.bag.foro_hub.model.dto.response.TopicResponse;

import java.util.List;

public interface TopicService {

  TopicResponse save(CreateTopicRequest request, Long authId);

  List<TopicResponse> findAll();

  TopicResponse findById(Long id);

  TopicResponse update(Long id, UpdateTopicRequest request , Long authId);

  void deleteById(Long id, Long authId);
}
