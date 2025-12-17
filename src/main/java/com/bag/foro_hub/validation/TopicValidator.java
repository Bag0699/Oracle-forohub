package com.bag.foro_hub.validation;

import com.bag.foro_hub.model.dto.request.CreateTopicRequest;

public interface TopicValidator {

  void validate(CreateTopicRequest request);
}
