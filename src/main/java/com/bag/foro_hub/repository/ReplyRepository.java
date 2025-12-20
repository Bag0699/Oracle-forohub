package com.bag.foro_hub.repository;

import com.bag.foro_hub.model.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

  Optional<Reply> findByTopicIdAndIsSolutionTrue(Long topicId);
}
