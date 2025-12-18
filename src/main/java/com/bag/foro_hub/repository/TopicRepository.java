package com.bag.foro_hub.repository;

import com.bag.foro_hub.model.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
  boolean existsByTitleAndMessage(String title, String message);

  boolean existsByTitleAndMessageAndIdNot(String title, String message, Long id);
}
