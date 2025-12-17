package com.bag.foro_hub.validation;

import com.bag.foro_hub.exceptions.ValidatorException;
import com.bag.foro_hub.model.dto.request.CreateTopicRequest;
import com.bag.foro_hub.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TopicUniqueValidator implements TopicValidator {

  private final TopicRepository topicRepository;

  @Override
  public void validate(CreateTopicRequest request) {
    if (topicRepository.existsByTitleAndMessage(request.title(), request.message())) {
      throw new ValidatorException("Ya existe un tema con ese titulo y mensaje");
    }
  }
}
