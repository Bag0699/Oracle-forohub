package com.bag.foro_hub.service;

import com.bag.foro_hub.model.dto.request.CreateTopicRequest;
import com.bag.foro_hub.model.dto.response.TopicResponse;

import java.util.List;

public interface TopiService {

  TopicResponse save(CreateTopicRequest request);

  List<TopicResponse> findAll();
}
