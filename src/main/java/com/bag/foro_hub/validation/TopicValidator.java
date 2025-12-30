package com.bag.foro_hub.validation;

import com.bag.foro_hub.model.dto.request.CreateTopicRequest;
import com.bag.foro_hub.model.dto.request.UpdateTopicRequest;

public interface TopicValidator {

  void validate(CreateTopicRequest request);

  void valideteForUpdate(UpdateTopicRequest request, Long id);
}
