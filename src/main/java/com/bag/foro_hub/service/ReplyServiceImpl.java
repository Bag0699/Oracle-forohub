package com.bag.foro_hub.service;

import com.bag.foro_hub.exceptions.ReplyNotFoundException;
import com.bag.foro_hub.exceptions.TopicNotFoundException;
import com.bag.foro_hub.exceptions.UserNotFoundException;
import com.bag.foro_hub.mapper.ReplyMapper;
import com.bag.foro_hub.model.dto.CreateReplyRequest;
import com.bag.foro_hub.model.dto.response.ReplyResponse;
import com.bag.foro_hub.model.entity.Reply;
import com.bag.foro_hub.model.entity.Topic;
import com.bag.foro_hub.model.entity.User;
import com.bag.foro_hub.repository.ReplyRepository;
import com.bag.foro_hub.repository.TopicRepository;
import com.bag.foro_hub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

  private final ReplyRepository replyRepository;
  private final TopicRepository topicRepository;
  private final UserRepository userRepository;
  private final ReplyMapper replyMapper;

  @Override
  public ReplyResponse save(CreateReplyRequest request) {
    Topic topic =
        topicRepository.findById(request.topicId()).orElseThrow(TopicNotFoundException::new);

    User user = userRepository.findById(request.userId()).orElseThrow(UserNotFoundException::new);

    Reply reply = new Reply();
    reply.setMessage(request.message());
    reply.setUser(user);
    reply.setTopic(topic);

    return replyMapper.toReplyResponse(replyRepository.save(reply));
  }

  @Override
  public List<ReplyResponse> findAll() {
    return replyRepository.findAll().stream().map(replyMapper::toReplyResponse).toList();
  }

  @Override
  public ReplyResponse findById(Long id) {
    return replyRepository
        .findById(id)
        .map(replyMapper::toReplyResponse)
        .orElseThrow(ReplyNotFoundException::new);
  }
}
